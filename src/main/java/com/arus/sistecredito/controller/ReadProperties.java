package com.arus.sistecredito.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    private String path;
    private final String CONFIG_PROPERTIES = "config.properties";


    public ReadProperties(){
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(getCONFIG_PROPERTIES())) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.path = properties.getProperty("directory");
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCONFIG_PROPERTIES() {
        return CONFIG_PROPERTIES;
    }
}
