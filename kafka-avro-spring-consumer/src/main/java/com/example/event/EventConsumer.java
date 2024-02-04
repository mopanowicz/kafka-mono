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
class EventConsumer {

    @KafkaListener(topics = "${event-consumer.topics.simple-one}", containerFactory = "eventListenerContainerFactory")
    void receiveEventOne(ConsumerRecord<String, SimpleOne> consumerRecord, Acknowledgment acknowledgment) {
        log.debug("receive record={}", consumerRecord);
        // do something with the record
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "${event-consumer.topics.simple-two}", containerFactory = "eventListenerContainerFactory")
    void receiveEventTwo(ConsumerRecord<String, SimpleTwo> consumerRecord, Acknowledgment acknowledgment) {
        log.debug("receive record={}", consumerRecord);
        // do something with the record
        acknowledgment.acknowledge();
    }
}
