package com.example;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class AvroMessage {
    private String f1;
    private BigDecimal amount;
    private Date date;
    private Timestamp timestamp;
//    private LocalDate localDate;
//    private LocalDateTime localDateTime;
}
