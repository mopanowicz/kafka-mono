package com.example;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
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
import java.util.Random;

@SpringBootApplication
public class KafkaAvroGenericProducerApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(KafkaAvroGenericProducerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroGenericProducerApplication.class, args);
	}

	@Value("${bootstrap.servers}")
	String bootstrapServers;
	@Value("${schema.registry.url}")
	String schemaRegistryUrl;
	@Value("${messages.topic}")
	String messagesTopic;

	Random random = new Random();

	@Override
	public void run(String... args) {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
		props.put(KafkaAvroSerializerConfig.AVRO_USE_LOGICAL_TYPE_CONVERTERS_CONFIG, true);
		props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
		KafkaProducer<String, GenericRecord> producer = new KafkaProducer<>(props);

		String messageSchema = """
        {
            "type":"record",
            "namespace":"com.example",
            "name":"AvroMessage",
            "fields":[
                {"name":"text","type":["null","string"]},
                {"name":"localDate","type":["null",{"type":"int","logicalType":"date"}]},
                {"name":"amount","type":["null",{"type":"bytes","logicalType":"decimal","precision":10,"scale":4}],"default":null},
                {"name":"localDateTime","type":["null",{"type":"long","logicalType":"local-timestamp-millis"}],"default":null}
            ]
        }
        """;
        Schema.Parser parser = new Schema.Parser();
		Schema schema = parser.parse(messageSchema);

		String key = "generic-key-" + random.nextInt();

		GenericRecord avroMessage = new GenericData.Record(schema);
		avroMessage.put("text", "generic-value-" + random.nextInt());
		avroMessage.put("localDate", LocalDate.now());
		avroMessage.put("amount", BigDecimal.valueOf(random.nextDouble()).setScale(4, RoundingMode.HALF_EVEN));
		avroMessage.put("localDateTime", LocalDateTime.now());

		log.info("key={} value={}", key, avroMessage);

		ProducerRecord<String, GenericRecord> producerRecord = new ProducerRecord<>(messagesTopic, key, avroMessage);
		try {
			producer.send(producerRecord);
			producer.flush();
		} finally {
			producer.close();
		}
	}
}
