package com.example.event;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@Setter
@Slf4j
@RequiredArgsConstructor
class EventProducerController {

    private final EventProducer eventProducer;

    Random random = new Random();

    @GetMapping("/produce-simple-one")
    EventProducerResult produceSimpleOne(
            @RequestParam(name = "topic", defaultValue = "${event-producer.topics.simple-one}", required = false) String topic,
            @RequestParam(name = "numberOfEvents", defaultValue = "1", required = false) int numberOfEvents
    ) throws InterruptedException, ExecutionException {
        for (int i = 0; i < numberOfEvents; i++) {
            SimpleOne event = SimpleOne.newBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setSent(System.currentTimeMillis())
                    .setText(RandomStringUtils.randomAlphanumeric(32))
                    .build();
            eventProducer.send(topic, event.getId(), event);
        }
        return new EventProducerResult(topic, SimpleOne.class.getName(), numberOfEvents);
    }

    @GetMapping("/produce-simple-two")
    EventProducerResult produceSimpleTwo(
            @RequestParam(name = "topic", defaultValue = "${event-producer.topics.simple-two}", required = false) String topic,
            @RequestParam(name = "numberOfEvents", defaultValue = "1", required = false) int numberOfEvents
    ) throws InterruptedException, ExecutionException {
        for (int i = 0; i < numberOfEvents; i++) {
            SimpleTwo event = SimpleTwo.newBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setSent(System.currentTimeMillis())
                    .setAmount(random.nextDouble())
                    .build();
            eventProducer.send(topic, event.getId(), event);
        }
        return new EventProducerResult(topic, SimpleTwo.class.getName(), numberOfEvents);
    }

    @GetMapping("/produce-logical-one")
    EventProducerResult produceLogicalOne(
            @RequestParam(name = "topic", defaultValue = "${event-producer.topics.logical-one}", required = false) String topic,
            @RequestParam(name = "numberOfEvents", defaultValue = "1", required = false) int numberOfEvents
    ) throws InterruptedException, ExecutionException {
        for (int i = 0; i < numberOfEvents; i++) {
            LogicalOne event = LogicalOne.newBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setSent(LocalDateTime.now())
                    .setText(RandomStringUtils.randomAlphanumeric(32))
                    .build();
            eventProducer.send(topic, event.getId(), event);
        }
        return new EventProducerResult(topic, LogicalOne.class.getName(), numberOfEvents);
    }

    @GetMapping("/produce-logical-two")
    EventProducerResult produceLogicalTwo(
            @RequestParam(name = "topic", defaultValue = "${event-producer.topics.logical-two}", required = false) String topic,
            @RequestParam(name = "numberOfEvents", defaultValue = "1", required = false) int numberOfEvents
    ) throws InterruptedException, ExecutionException {
        for (int i = 0; i < numberOfEvents; i++) {
            LogicalTwo event = LogicalTwo.newBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setSent(LocalDateTime.now())
                    .setAmount(BigDecimal.valueOf(random.nextDouble()).setScale(4, RoundingMode.HALF_EVEN))
                    .build();
            eventProducer.send(topic, event.getId(), event);
        }
        return new EventProducerResult(topic, LogicalTwo.class.getName(), numberOfEvents);
    }
}
