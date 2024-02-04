package com.example.event;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service("reflectedEventProducer")
@Setter
@Slf4j
public class ReflectedEventProducer {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    @Value("${reflected-event-producer.blocking:false}")
    boolean blocking;

    public ReflectedEventProducer(@Qualifier("reflectedKafkaTemplate") KafkaTemplate<Object, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, Object key, Object event) throws InterruptedException, ExecutionException {
        log.debug("send topic={} key={} event={}", topic, key, event);
        ProducerRecord<Object, Object> producerRecord = new ProducerRecord<>(topic, key, event);
        CompletableFuture<SendResult<Object, Object>> future = kafkaTemplate.send(producerRecord);
        if (blocking) {
            future.get();
        }
    }
}
