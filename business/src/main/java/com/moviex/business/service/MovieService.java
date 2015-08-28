package com.moviex.business.service;

import com.moviex.persistence.entity.movie.Movie;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {

    Movie findById(String id);

    void upsert(Movie movie);

    void upsertAsync(Iterable<Movie> movies);

    void upsert(Iterable<Movie> movies);
}
