package com.example.kafkaavroconsumer;

import org.apache.avro.generic.GenericRecord;
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
public class KafkaAvroConsumerApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(KafkaAvroConsumerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroConsumerApplication.class, args);
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
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");

		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroDeserializer");
		props.put("schema.registry.url", schemaRegistryUrl);

		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		final Consumer<String, GenericRecord> consumer = new KafkaConsumer<String, GenericRecord>(props);
		consumer.subscribe(Arrays.asList(messagesTopic));

		try {
			while (true) {
				ConsumerRecords<String, GenericRecord> messages = consumer.poll(100);
				for (ConsumerRecord<String, GenericRecord> avroMessage : messages) {
					log.info("offset = {}, key = {}, value = {}}", avroMessage.offset(), avroMessage.key(), avroMessage.value());
				}
			}
		} finally {
			consumer.close();
		}
	}
}
