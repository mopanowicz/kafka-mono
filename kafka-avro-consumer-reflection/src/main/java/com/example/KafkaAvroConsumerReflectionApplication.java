package com.example;

import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Properties;

@SpringBootApplication
public class KafkaAvroConsumerReflectionApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(KafkaAvroConsumerReflectionApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroConsumerReflectionApplication.class, args);
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

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "reflection-group-1");

		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, io.confluent.kafka.streams.serdes.avro.ReflectionAvroDeserializer.class);
		props.put(KafkaAvroDeserializerConfig.AVRO_REFLECTION_ALLOW_NULL_CONFIG, true);
		props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);

		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		final Consumer<String, AvroMessage> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList(messagesTopic));

		try {
			while (true) {
				ConsumerRecords<String, AvroMessage> messages = consumer.poll(100);
				for (ConsumerRecord<String, AvroMessage> avroMessage : messages) {
					AvroMessage value = avroMessage.value();
					log.info("offset = {}, key = {}, value = {}", avroMessage.offset(), avroMessage.key(), avroMessage.value());
				}
			}
		} finally {
			consumer.close();
		}
	}

}
