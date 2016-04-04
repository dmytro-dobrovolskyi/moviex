package com.moviex.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAsync
public class BusinessConfig {

    @Bean
    public RestOperations restTemplate() {
        return new RestTemplate();
    }
}
