package com.rebuy.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Configuration
public class UpstageConfig {

    @Value("${upstage.api.url}")
    private String apiUrl;

    @Value("${upstage.api.key}")
    private String apiKey;

    @Value("${upstage.api.timeout}")
    private int timeout;

    @Bean
    public WebClient upstageWebClient() {
        return WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}

