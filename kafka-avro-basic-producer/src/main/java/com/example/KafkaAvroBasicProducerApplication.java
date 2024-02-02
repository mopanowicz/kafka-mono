package com.example;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class KafkaAvroBasicProducerApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(KafkaAvroBasicProducerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroBasicProducerApplication.class, args);
	}

	@Value("${bootstrap.servers}")
	String bootstrapServers;
	@Value("${schema.registry.url}")
	String schemaRegistryUrl;

	Random random = new Random();

	@Override
	public void run(String... args) {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
		props.put(KafkaAvroSerializerConfig.AVRO_USE_LOGICAL_TYPE_CONVERTERS_CONFIG, true);
		props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);

//		props.put(AbstractKafkaSchemaSerDeConfig.AUTO_REGISTER_SCHEMAS, false);
//		props.put(AbstractKafkaSchemaSerDeConfig.USE_LATEST_VERSION, true);

		KafkaProducer<String, Object> producer = new KafkaProducer<>(props);

		EventOne event = EventOne.newBuilder()
				.setId(UUID.randomUUID().toString())
				.setSent(LocalDateTime.now())
				.setText(RandomStringUtils.randomAlphanumeric(32))
				.build();

		ProducerRecord<String, Object> producerRecord = new ProducerRecord<>("event-one", event.getId(), event);
		try {
			log.info("sending {}", producerRecord);
			producer.send(producerRecord);
			producer.flush();
		} finally {
			producer.close();
		}
	}
}
