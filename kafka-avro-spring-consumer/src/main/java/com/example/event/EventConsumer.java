package com.example.event;

import com.example.EventOne;
import com.example.EventTwo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventConsumer {

    @KafkaListener(topics = "${event-consumer.event-one-topic}", containerFactory = "eventListenerContainerFactory")
    void receiveEventOne(ConsumerRecord<String, EventOne> consumerRecord, Acknowledgment acknowledgment) {
        log.debug("receive record={}", consumerRecord);
        // do something with the record
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "${event-consumer.event-two-topic}", containerFactory = "eventListenerContainerFactory")
    void receiveEventTwo(ConsumerRecord<String, EventTwo> consumerRecord, Acknowledgment acknowledgment) {
        log.debug("receive record={}", consumerRecord);
        // do something with the record
        acknowledgment.acknowledge();
    }
}
