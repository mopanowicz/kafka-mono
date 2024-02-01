package com.example;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@Setter
@Slf4j
@RequiredArgsConstructor
class EventProducerController {

    private final EventProducer eventProducer;

    @Value("${event-producer.topic}")
    String eventTopic;

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
            eventProducer.send(eventTopic, event.getId(), event);
        }
        return new EventProducerResult(EventOne.getClassSchema().getName(), numberOfEvents);
    }
}
