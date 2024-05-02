package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("main");
        try {
            Class.forName("com.informix.jdbc.IfxDriver");
        } catch (Exception e) {
            log.error("Loading of Informix JDBC driver failed", e);
        }
    }
}