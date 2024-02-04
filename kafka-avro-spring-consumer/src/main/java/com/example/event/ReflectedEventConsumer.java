package com.example.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
class ReflectedEventConsumer {

    @KafkaListener(topics = "${reflected-event-consumer.topics.reflected-one}", containerFactory = "reflectedEventListenerContainerFactory")
    void receiveReflectedOne(ConsumerRecord<Object, Object> consumerRecord, Acknowledgment acknowledgment) {
        log.debug("receiveReflectedOne record={}", consumerRecord);
        // do something with the record
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "${reflected-event-consumer.topics.reflected-two}", containerFactory = "reflectedEventListenerContainerFactory")
    void receiveReflectedTwo(ConsumerRecord<Object, Object> consumerRecord, Acknowledgment acknowledgment) {
        log.debug("receiveReflectedTwo record={}", consumerRecord);
        // do something with the record
        acknowledgment.acknowledge();
    }
}
