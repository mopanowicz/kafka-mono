package com.example.event;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;

@Setter
@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = "reflected-event-producer")
class ReflectedEventProducerConfig {

    Map<String, String> properties;

    @Bean("reflectedEventProducerFactory")
    ProducerFactory<Object, Object> reflectedEventProducerFactory() {
        Map<String, Object> configs = properties.entrySet()
                .stream()
                .filter(e -> StringUtils.hasText(e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> String.valueOf(e.getValue())));
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean("reflectedKafkaTemplate")
    public KafkaTemplate<Object, Object> reflectedKafkaTemplate(@Qualifier("reflectedEventProducerFactory") ProducerFactory<Object, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
