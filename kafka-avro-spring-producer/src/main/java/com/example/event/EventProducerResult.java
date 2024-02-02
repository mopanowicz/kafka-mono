package com.example.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class EventProducerResult {
    String eventType;
    int numberOfEvents;
}
