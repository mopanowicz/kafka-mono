package com.example;

import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

@SpringBootApplication
public class KafkaAvroProducerApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(KafkaAvroProducerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroProducerApplication.class, args);
	}

	@Value("${bootstrap.servers:localhost:9092}")
	String bootstrapServers;
	@Value("${schema.registry.url:http://localhost:8081}")
	String schemaRegistryUrl;
	@Value("${messages.topic}")
	String messagesTopic;

	@Override
	public void run(String... args) {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
		props.put(KafkaAvroSerializerConfig.AVRO_USE_LOGICAL_TYPE_CONVERTERS_CONFIG, true);
		props.put("schema.registry.url", schemaRegistryUrl);
		KafkaProducer producer = new KafkaProducer(props);

		String messageSchema = """
		{
			"type":"record",
			"namespace":"com.example",
			"name":"AvroMessage",
			"fields":[
				{"name":"f1","type":["null","string"]},
				{"name":"date","type":["null",{"type":"int","logicalType":"date"}]},
				{"name":"amount","type":["null",{"type":"bytes","logicalType":"decimal","precision":10,"scale":4}],"default":null},
				{"name":"dateTime","type":["null",{"type":"long","logicalType":"local-timestamp-millis"}],"default":null}
			]
		}
		""";
		Schema.Parser parser = new Schema.Parser();
		Schema schema = parser.parse(messageSchema);

		String key = "generic-key-"+ (int)(Math.random() * 1000);

		GenericRecord avroMessage = new GenericData.Record(schema);
		avroMessage.put("f1", "string" + (int)(Math.random() * 1000));
		avroMessage.put("date", LocalDate.now());
		avroMessage.put("amount", new BigDecimal(Math.random() * 10000).setScale(4, RoundingMode.HALF_EVEN));
		avroMessage.put("dateTime", LocalDateTime.now());

		log.info("key={} value={}", key, avroMessage);

		ProducerRecord<Object, Object> record = new ProducerRecord<>(messagesTopic, key, avroMessage);
		try {
			producer.send(record).get();
			producer.flush();
		} catch(Exception e) {
			log.error("exception", e);
		} finally {
			producer.close();
		}
	}
}
