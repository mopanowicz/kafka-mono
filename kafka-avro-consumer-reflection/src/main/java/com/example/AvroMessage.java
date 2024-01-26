package com.example;

public class AvroMessage {

    private String f1;

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    @Override
    public String toString() {
        return "AvroMessage{" +
                "f1='" + f1 + '\'' +
                '}';
    }
}
