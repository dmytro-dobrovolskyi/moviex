package com.moviex.business.config;

import com.moviex.persistence.config.PersistenceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.moviex.business.service")
@Import(PersistenceConfig.class)
public class BusinessConfig {}
