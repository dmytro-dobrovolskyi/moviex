package com.moviex.business.service;

import com.moviex.persistence.entity.movie.Movie;
import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

    Movie findById(String id);

    void upsert(Movie movie);

    void upsertAsync(Iterable<Movie> movies);

    void upsert(Iterable<Movie> movies);

    void loadMoviesBasedOnMetadata(List<MovieSearchMetadata> searchMetadataList);
}
