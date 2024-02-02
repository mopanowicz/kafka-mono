package com.example;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@SpringBootApplication
public class KafkaAvroBasicConsumerApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(KafkaAvroBasicConsumerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroBasicConsumerApplication.class, args);
	}

	@Value("${bootstrap.servers}")
	String bootstrapServers;
	@Value("${schema.registry.url}")
	String schemaRegistryUrl;

	@Override
	public void run(String... args) {
		Properties props = new Properties();

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "basic-group-1");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroDeserializer.class);
		props.put(KafkaAvroDeserializerConfig.AVRO_USE_LOGICAL_TYPE_CONVERTERS_CONFIG, true);
		props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
		props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		final Consumer<String, Object> consumer = new KafkaConsumer<>(props);

        try (consumer) {
            consumer.subscribe(Arrays.asList("event-one", "event-two"));
            while (true) {
                ConsumerRecords<String, Object> messages = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, Object> message : messages) {
                    log.info("offset = {} key = {} value class = {} value = {}", message.offset(), message.key(), message.value().getClass().getName(), message.value());
                }
            }
        }
	}
}
