package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.config")
public class AppConfiguration {
    private String author;
    private String connectionString;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getAuthor() {
        return author;
    }

    public String getConnectionString() {
        return connectionString;
    }
}
