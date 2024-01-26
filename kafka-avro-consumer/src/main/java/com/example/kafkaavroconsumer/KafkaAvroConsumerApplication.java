package com.example.kafkaavroconsumer;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Properties;

@SpringBootApplication
public class KafkaAvroConsumerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroConsumerApplication.class, args);
	}

	@Value("${bootstrap.servers:localhost:9092}")
	String bootstrapServers;
	@Value("${schema.registry.url:http://localhost:8081}")
	String schemaRegistryUrl;

	@Override
	public void run(String... args) {
		Properties props = new Properties();

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");

		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroDeserializer");
		props.put("schema.registry.url", schemaRegistryUrl);

		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		String topic = "topic1";
		final Consumer<String, GenericRecord> consumer = new KafkaConsumer<String, GenericRecord>(props);
		consumer.subscribe(Arrays.asList(topic));

		try {
			while (true) {
				ConsumerRecords<String, GenericRecord> records = consumer.poll(100);
				for (ConsumerRecord<String, GenericRecord> record : records) {
					System.out.printf("offset = %d, key = %s, value = %s \n", record.offset(), record.key(), record.value());
				}
			}
		} finally {
			consumer.close();
		}
	}
}
