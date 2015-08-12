package com.moviex.business;

import com.moviex.business.config.BusinessConfig;
import com.moviex.business.service.MovieService;
import com.moviex.persistence.entity.Movie;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BusinessTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BusinessConfig.class);
        System.out.println(applicationContext.getBean(MovieService.class).findById("w4e23ef3"));
    }
}
