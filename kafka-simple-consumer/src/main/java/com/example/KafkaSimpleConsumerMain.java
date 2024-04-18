package com.example;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class KafkaSimpleConsumerMain {

    static Logger log = LoggerFactory.getLogger(KafkaSimpleConsumerMain.class);
    static Configuration configuration = Configuration.getInstance();

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.putAll(configuration.getAll());
        try (Consumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(List.of("test-messages", "test-messages-redirect"));
            log.info("assignment {}", consumer.assignment());
            while (true) {
                ConsumerRecords<String, String> messages = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> message : messages) {
//                    log.info("topic={} offset={} key={} value={}", message.topic(), message.offset(), message.key(), message.value());
                    log.info("topic={} partition={} offset={}", message.topic(), message.partition(), message.offset());
                }
            }
        }
    }
}