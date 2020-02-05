package com.albo.marveljavaexam.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarvelClientConfig {

    @Value(value = "${marvel-api.config.apiKey}")
    private String apiKey;

    @Value(value = "${marvel-api.config.ts}")
    private String ts;

    @Value(value = "${marvel-api.config.hash}")
    private String hash;

    @Bean
    public MarvelAuthorization gitlabConfig() {
        return MarvelAuthorization.builder()
                .ts(this.ts)
                .apiKey(this.apiKey)
                .hash(this.hash)
                .build();
    }
}
