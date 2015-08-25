package com.moviex.business.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviex.persistence.config.PersistenceConfig;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableTransactionManagement
@EnableAsync
@ComponentScan("com.moviex.business.service")
@Import(PersistenceConfig.class)
public class BusinessConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
