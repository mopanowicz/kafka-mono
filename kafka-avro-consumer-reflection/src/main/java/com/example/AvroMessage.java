package com.example;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AvroMessage {
    private String f1;
    private BigDecimal amount;
    private Date date;
//    private LocalDate localDate;
//    private LocalDateTime localDateTime;
}
