package com.moviex.persistence;

import com.moviex.persistence.config.PersistenceConfig;
import com.moviex.persistence.entity.Movie;
import com.moviex.persistence.repository.MovieRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PersistenceTest {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(PersistenceConfig.class).getBean(MovieRepository.class).save(new Movie("w4e23ef3", "Game of Thrones"));
    }
}
