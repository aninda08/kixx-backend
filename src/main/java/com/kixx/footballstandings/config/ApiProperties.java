package com.kixx.footballstandings.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties
@Getter
@Setter
public class ApiProperties {
    private ApiFootball apiFootball;

    @Getter
    @Setter
    public static class ApiFootball {
        private String apiKey;
    }
}
