package com.example.event;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service("eventProducer")
@Setter
@Slf4j
class EventProducer {

    private final KafkaTemplate<String, SpecificRecord> kafkaTemplate;

    @Value("${event-producer.blocking:false}")
    boolean blocking;

    public EventProducer(@Qualifier("eventKafkaTemplate") KafkaTemplate<String, SpecificRecord> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, String key, SpecificRecord event) throws InterruptedException, ExecutionException {
        log.debug("send topic={} key={} event={}", topic, key, event);
        ProducerRecord<String, SpecificRecord> producerRecord = new ProducerRecord<>(topic, key, event);
        CompletableFuture<SendResult<String, SpecificRecord>> future = kafkaTemplate.send(producerRecord);
        if (blocking) {
            future.get();
        }
    }
}
