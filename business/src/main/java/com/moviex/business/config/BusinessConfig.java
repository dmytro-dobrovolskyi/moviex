package com.moviex.business.config;

import com.moviex.persistence.config.PersistenceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.moviex.business.service")
@Import(PersistenceConfig.class)
public class BusinessConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
