package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

public class Configuration {

    private final static Configuration instance = new Configuration();

    private final Properties configuration = new Properties();

    private Configuration() {
        File file = new File( "configuration.properties");
        if (file.canRead()) {
            try {
                configuration.load(new FileInputStream(file));
            } catch (Exception e) {
                e.printStackTrace();
            }
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
