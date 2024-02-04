package com.example.event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class ReflectOne {
    Long id;
    String text;
    BigDecimal amount;
    Date date;
    Integer nullableInteger;
    int nonNullableInt;
    Double doubleAmount;
}
