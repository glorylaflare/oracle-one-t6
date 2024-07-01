package org.example.modules;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Config instance;
    private Properties properties;

    private Config() throws IOException {
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("config.properties");
        properties.load(fileInputStream);
        fileInputStream.close();
    }

    public static Config getInstance() throws IOException {
        if(instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
