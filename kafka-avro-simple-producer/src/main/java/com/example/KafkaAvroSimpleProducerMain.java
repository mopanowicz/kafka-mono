package com.example;

import com.example.event.SimpleOne;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.UUID;

public class KafkaAvroSimpleProducerMain {

    static Logger log = LoggerFactory.getLogger(KafkaAvroSimpleProducerMain.class);
    static Configuration configuration = Configuration.getInstance();

    public static void main(String[] args) {
        int count = 1;
        int length = 64;
        Properties properties = new Properties();
        properties.putAll(configuration.getAll());
        try (KafkaProducer<Object, Object> producer = new KafkaProducer<>(properties)) {
            log.info("sending {} records", count);
            for (int i = 0; i < count; i++) {
                String id = UUID.randomUUID().toString();
                SimpleOne message = SimpleOne.newBuilder().setId(id).setSent(System.currentTimeMillis()).setText(RandomStringUtils.random(length, true, true)).build();
                ProducerRecord<Object, Object> record = new ProducerRecord<>(
                        "simple-one",
                        id,
                        message
                        );
                producer.send(record);
            }
            producer.flush();
            log.info("sent {} records", count);
        } catch(Exception e) {
            log.error("exception", e);
        }
    }
}