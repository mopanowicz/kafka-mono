package com.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaAvroSimpleProducerMain {

    static Logger log = LoggerFactory.getLogger(KafkaAvroSimpleProducerMain.class);
    static Configuration configuration = Configuration.getInstance();

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.putAll(configuration.getAll());
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            ProducerRecord<String, String> record = new ProducerRecord<>(
                    "test-messages",
                    "key-"+ (long)(Math.random() * 1000),
                    "value-"+ (long)(Math.random() * 1000));
            log.info("sending {}", record);
            producer.send(record);
            producer.flush();
        } catch(Exception e) {
            log.error("exception", e);
        }
    }
}