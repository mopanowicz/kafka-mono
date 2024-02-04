package com.example.event;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${event-producer.event-one-topic}")
    String eventOneTopic;

    @Value("${event-producer.event-two-topic}")
    String eventTwoTopic;

    Random random = new Random();

    @GetMapping("/produce-random-event-one")
    EventProducerResult produceRandomEventOne(
            @RequestParam(name = "numberOfEvents", defaultValue = "1", required = false) int numberOfEvents
    ) throws InterruptedException, ExecutionException {
        for (int i = 0; i < numberOfEvents; i++) {
            EventOne event = EventOne.newBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setSent(LocalDateTime.now())
                    .setText(RandomStringUtils.randomAlphanumeric(32))
                    .build();
            eventProducer.send(eventOneTopic, event.getId(), event);
        }
        return new EventProducerResult(EventOne.getClassSchema().getName(), numberOfEvents);
    }

    @GetMapping("/produce-random-event-two")
    EventProducerResult produceRandomEventTwo(
            @RequestParam(name = "numberOfEvents", defaultValue = "1", required = false) int numberOfEvents
    ) throws InterruptedException, ExecutionException {
        for (int i = 0; i < numberOfEvents; i++) {
            EventTwo event = EventTwo.newBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setSent(LocalDateTime.now())
                    .setAmount(BigDecimal.valueOf(random.nextDouble()).setScale(4, RoundingMode.HALF_EVEN))
                    .build();
            eventProducer.send(eventTwoTopic, event.getId(), event);
        }
        return new EventProducerResult(EventOne.getClassSchema().getName(), numberOfEvents);
    }
}
