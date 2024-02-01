package com.example;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Setter
@Slf4j
@RequiredArgsConstructor
public class EventProducer {

    private final KafkaTemplate<String, SpecificRecord> kafkaTemplate;

    @Value("${event-producer.blocking:false}")
    boolean blocking;

    public void send(String topic, String key, SpecificRecord event) throws InterruptedException, ExecutionException {
        log.debug("send topic={} key={} event={}", topic, key, event);
        ProducerRecord<String, SpecificRecord> producerRecord = new ProducerRecord<>(topic, key, event);
        CompletableFuture<SendResult<String, SpecificRecord>> future = kafkaTemplate.send(producerRecord);
        if (blocking) {
            future.get();
        }
    }
}
