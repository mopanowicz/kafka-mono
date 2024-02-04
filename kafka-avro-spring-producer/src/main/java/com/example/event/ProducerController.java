package com.example.event;

import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
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
class ProducerController {

    private final EventProducer eventProducer;
    private final ReflectedEventProducer reflectedEventProducer;

    Random random = new Random();

    public ProducerController(@Qualifier("eventProducer") EventProducer eventProducer, @Qualifier("reflectedEventProducer") ReflectedEventProducer reflectedEventProducer) {
        this.eventProducer = eventProducer;
        this.reflectedEventProducer = reflectedEventProducer;
    }

    @GetMapping("/produce-simple-one")
    ProducerResult produceSimpleOne(
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
        return new ProducerResult(topic, SimpleOne.class.getName(), numberOfEvents);
    }

    @GetMapping("/produce-simple-two")
    ProducerResult produceSimpleTwo(
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
        return new ProducerResult(topic, SimpleTwo.class.getName(), numberOfEvents);
    }

    @GetMapping("/produce-logical-one")
    ProducerResult produceLogicalOne(
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
        return new ProducerResult(topic, LogicalOne.class.getName(), numberOfEvents);
    }

    @GetMapping("/produce-logical-two")
    ProducerResult produceLogicalTwo(
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
        return new ProducerResult(topic, LogicalTwo.class.getName(), numberOfEvents);
    }

    @GetMapping("/produce-reflected-one")
    ProducerResult produceReflectedOne(
            @RequestParam(name = "topic", defaultValue = "${reflected-event-producer.topics.reflected-one}", required = false) String topic,
            @RequestParam(name = "numberOfEvents", defaultValue = "1", required = false) int numberOfEvents
    ) throws InterruptedException, ExecutionException {
        for (int i = 0; i < numberOfEvents; i++) {
            ReflectedOne event = new ReflectedOne();
            event.setId(UUID.randomUUID().toString());
            event.setSent(System.currentTimeMillis());
            event.setText(RandomStringUtils.randomAlphanumeric(32));
            reflectedEventProducer.send(topic, event.getId(), event);
        }
        return new ProducerResult(topic, ReflectedOne.class.getName(), numberOfEvents);
    }

    @GetMapping("/produce-reflected-two")
    ProducerResult produceReflectedTwo(
            @RequestParam(name = "topic", defaultValue = "${reflected-event-producer.topics.reflected-two}", required = false) String topic,
            @RequestParam(name = "numberOfEvents", defaultValue = "1", required = false) int numberOfEvents
    ) throws InterruptedException, ExecutionException {
        for (int i = 0; i < numberOfEvents; i++) {
            ReflectedTwo event = new ReflectedTwo();
            event.setId(UUID.randomUUID().toString());
            event.setSent(System.currentTimeMillis());
            event.setAmount(random.nextDouble());
            reflectedEventProducer.send(topic, event.getId(), event);
        }
        return new ProducerResult(topic, ReflectedTwo.class.getName(), numberOfEvents);
    }
}
