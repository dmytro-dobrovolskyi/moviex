package com.moviex.business.service;

import com.moviex.persistence.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

    Movie findById(String id);

    List<Movie> findByTitle(String title);

    void upsert(Movie movie);
}
