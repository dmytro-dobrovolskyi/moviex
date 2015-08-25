package com.moviex.business.service;

import com.moviex.persistence.entity.movie.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

@Service
public interface MovieService {

    Movie findById(String id);

    void upsert(Movie movie);

    void upsertAsync(Iterable<Movie> movies);

    void upsertWhenReady(List<Future<? extends Iterable<Movie>>> futureMovies);

    Future<Set<Movie>> requestByTitleAsync(String title);

    Set<Movie> requestByTitle(String title);
}
