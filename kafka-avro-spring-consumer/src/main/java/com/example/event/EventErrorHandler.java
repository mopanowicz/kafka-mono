package com.example.event;

import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.stereotype.Component;

@Component
class EventErrorHandler implements CommonErrorHandler {
    // override handle... methods and handle errors
}
