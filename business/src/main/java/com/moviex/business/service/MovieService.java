package com.moviex.business.service;

import com.moviex.persistence.entity.movie.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

    Movie findById(String id);

    void upsert(Movie movie);

    void batchUpsert(List<Movie> movie);
}
