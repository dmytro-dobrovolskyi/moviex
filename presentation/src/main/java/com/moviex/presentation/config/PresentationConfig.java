package com.moviex.presentation.config;

import com.moviex.business.config.BusinessConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableEntityLinks
@ComponentScan({"com.moviex.presentation.controller", "com.moviex.presentation.advice"})
@Import({BusinessConfig.class, RestConfig.class})
public class PresentationConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/client/**")
                .addResourceLocations("/client/");
    }
}
