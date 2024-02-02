package com.example.event;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class Event {
    String text;
    BigDecimal amount;
    Date date;
//    LocalDate localDate;
//    LocalDateTime localDateTime;
}
