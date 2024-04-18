package com.example;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.UUID;

public class KafkaSimpleProducerMain {

    static Logger log = LoggerFactory.getLogger(KafkaSimpleProducerMain.class);
    static Configuration configuration = Configuration.getInstance();

    public static void main(String[] args) {
        int count = 16;
        int length = 32;
        Properties properties = new Properties();
        properties.putAll(configuration.getAll());
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            log.info("sending {} records", count);
            for (int i = 0; i < count; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>(
                        "test-messages",
                        """
                        {
                            "id": "%s"
                        }
                        """.formatted(UUID.randomUUID()),
                        """
                        {
                            "cargo": "%s"
                        }
                        """.formatted(RandomStringUtils.random(length, true, true)));
                producer.send(record);
            }
            producer.flush();
            log.info("sent {} records", count);
        } catch(Exception e) {
            log.error("exception", e);
        }
    }
}