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
import java.util.Collections;
import java.util.Properties;

@SpringBootApplication
public class KafkaAvroReflectionConsumerApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(KafkaAvroReflectionConsumerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KafkaAvroReflectionConsumerApplication.class, args);
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
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "reflection-group-1");

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, io.confluent.kafka.streams.serdes.avro.ReflectionAvroDeserializer.class);
        props.put(KafkaAvroDeserializerConfig.AVRO_REFLECTION_ALLOW_NULL_CONFIG, true);
        props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);

        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        final Consumer<String, Object> consumer = new KafkaConsumer<>(props);

        try (consumer) {
            consumer.subscribe(Collections.singletonList(messagesTopic));
            while (true) {
                ConsumerRecords<String, Object> messages = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, Object> consumerRecord : messages) {
                    log.info("offset = {} key = {} value class = {} value = {}", consumerRecord.offset(), consumerRecord.key(), consumerRecord.value().getClass().getName(), consumerRecord.value());
                }
            }
        }
    }
}
