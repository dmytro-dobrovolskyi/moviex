package com.moviex.business.service;

import com.moviex.persistence.entity.movie.Movie;
import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface MovieService {

    Movie findById(String id);

    List<MovieSearchMetadata> findByTitle(String title);

    void upsert(Movie movie);

    void batchUpsert(List<Movie> movie);
}
