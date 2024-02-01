package com.example;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

@SpringBootApplication
public class KafkaAvroGenericConsumerApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(KafkaAvroGenericConsumerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroGenericConsumerApplication.class, args);
	}

	@Value("${bootstrap.servers}")
	String bootstrapServers;
	@Value("${schema.registry.url}")
	String schemaRegistryUrl;
	@Value("${messages.topic}")
	String messagesTopic;

	@Override
	public void run(String... args) {
		Properties props = new Properties();

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "generic-group-1");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroDeserializer.class);
		props.put(KafkaAvroDeserializerConfig.AVRO_USE_LOGICAL_TYPE_CONVERTERS_CONFIG, true);
		props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		final Consumer<String, GenericRecord> consumer = new KafkaConsumer<String, GenericRecord>(props);

        try (consumer) {
            consumer.subscribe(Collections.singletonList(messagesTopic));
            while (true) {
                ConsumerRecords<String, GenericRecord> messages = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, GenericRecord> avroMessage : messages) {
                    log.info("offset = {}, key = {}, value = {}", avroMessage.offset(), avroMessage.key(), avroMessage.value());
                }
            }
        }
	}
}
