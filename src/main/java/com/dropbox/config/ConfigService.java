package com.dropbox.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigService {
    private Properties properties=new Properties();
    private String path;
    private InputStream input = null;

    public ConfigService(String path) {
        this.path=path;

    }
    public ConfigService load()throws IOException{
        properties.load(new FileInputStream(path));
        return this;

    }
    public String get(String key){
        return properties.getProperty(key);

    }
    public boolean getBoolean(String key){
        return Boolean.parseBoolean(get(key));

    }
}
