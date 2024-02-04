package com.example;

import com.example.event.EventX;
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
import java.time.Instant;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

@SpringBootApplication
public class KafkaAvroReflectionProducerApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(KafkaAvroReflectionProducerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroReflectionProducerApplication.class, args);
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
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.streams.serdes.avro.ReflectionAvroSerializer.class);
		props.put(KafkaAvroSerializerConfig.AVRO_REFLECTION_ALLOW_NULL_CONFIG, true);
		props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
		KafkaProducer<String, Object> producer = new KafkaProducer<>(props);

		String key = "reflection-key-1";
		EventX event = EventX.builder()
				.text("reflection-value-" + random.nextInt())
				.amount(BigDecimal.valueOf(random.nextDouble()).setScale(4, RoundingMode.HALF_EVEN))
				.date(Date.from(Instant.now()))
				.build();

		ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(messagesTopic, key, event);
		try {
			log.info("sending {}", producerRecord);
			producer.send(producerRecord);
			producer.flush();
		} finally {
			producer.close();
		}
	}
}
