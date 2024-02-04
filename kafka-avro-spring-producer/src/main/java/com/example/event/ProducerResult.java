package com.example.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ProducerResult {
    String topic;
    String eventType;
    int numberOfEvents;
}
