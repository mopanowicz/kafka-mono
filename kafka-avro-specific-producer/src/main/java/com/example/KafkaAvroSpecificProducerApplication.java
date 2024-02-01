package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaAvroSpecificProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroSpecificProducerApplication.class, args);
	}

}
