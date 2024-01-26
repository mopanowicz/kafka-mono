package com.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class KafkaAvroProducerReflectionApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(KafkaAvroProducerReflectionApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroProducerReflectionApplication.class, args);
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
		props.put("schema.registry.url", schemaRegistryUrl);
		KafkaProducer producer = new KafkaProducer(props);

		String key = "reflection-key-1";
		AvroMessage avroMessage = new AvroMessage();
		avroMessage.setF1("value" + (Math.random() * 1000));

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
