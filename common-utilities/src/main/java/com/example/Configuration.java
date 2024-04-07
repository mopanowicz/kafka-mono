package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

public class Configuration {

    static Logger log = LoggerFactory.getLogger(Configuration.class);

    private final static Configuration instance = new Configuration();

    private final Properties configuration = new Properties();

    private Configuration() {
        String configurationFile = "configuration.properties";
        File file = new File(configurationFile);
        if (file.canRead()) {
            try {
                configuration.load(new FileInputStream(file));
            } catch (Exception e) {
                log.error("exception", e);
            }
            log.info("loaded {}", configurationFile);
        } else {
            log.warn("configuration file {} not found", configurationFile);
        }
    }

    public static Configuration getInstance() {
        return instance;
    }

    public String getProperty(String name) {
        return configuration.getProperty(name);
    }

    public Map<Object, Object> getAll() {
        return configuration;
    }
}
