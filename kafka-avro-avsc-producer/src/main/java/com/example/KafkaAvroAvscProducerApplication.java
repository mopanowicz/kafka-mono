package com.example;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;

@SpringBootApplication
public class KafkaAvroAvscProducerApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(KafkaAvroAvscProducerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroAvscProducerApplication.class, args);
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
		KafkaProducer<String, AvroMessage> producer = new KafkaProducer<>(props);

		String key = "avsc-key-1";
		AvroMessage avroMessage = new AvroMessage();
		avroMessage.setText("avsc-value-" + random.nextInt());
		avroMessage.setAmount(BigDecimal.valueOf(random.nextDouble()).setScale(4, RoundingMode.HALF_EVEN));
		avroMessage.setLocalDate(LocalDate.now());
		avroMessage.setLocalDateTime(LocalDateTime.now());
		avroMessage.setAmount2(BigDecimal.valueOf(random.nextDouble()).setScale(4, RoundingMode.HALF_EVEN));

		ProducerRecord<String,AvroMessage> producerRecord = new ProducerRecord<>(messagesTopic, key, avroMessage);
		try {
			log.info("sending {}", producerRecord);
			producer.send(producerRecord);
			producer.flush();
		} finally {
			producer.close();
		}
	}
}
