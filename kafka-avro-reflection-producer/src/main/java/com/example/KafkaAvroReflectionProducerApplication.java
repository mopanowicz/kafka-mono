package com.example;

import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
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
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Properties;

@SpringBootApplication
public class KafkaAvroReflectionProducerApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(KafkaAvroReflectionProducerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroReflectionProducerApplication.class, args);
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
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.streams.serdes.avro.ReflectionAvroSerializer.class);
		props.put(KafkaAvroSerializerConfig.AVRO_REFLECTION_ALLOW_NULL_CONFIG, true);
		props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
		KafkaProducer producer = new KafkaProducer(props);

		String key = "reflection-key-1";
		AvroMessage avroMessage = new AvroMessage();
		avroMessage.setF1("reflection-value-" + (long)(Math.random() * 1000));
		avroMessage.setAmount(new BigDecimal(Math.random() * 10000).setScale(4, RoundingMode.HALF_EVEN));
		avroMessage.setDate(Date.from(Instant.now()));
		avroMessage.setTimestamp(Timestamp.from(Instant.now()));
//		avroMessage.setLocalDate(LocalDate.now());
//		avroMessage.setLocalDateTime(LocalDateTime.now());

		ProducerRecord<Object, Object> record = new ProducerRecord<>(messagesTopic, key, avroMessage);
		try {
			log.info("sending {}", record);
			producer.send(record).get();
			producer.flush();
		} catch(Exception e) {
			log.error("exception", e);
		} finally {
			producer.close();
		}
	}
}
