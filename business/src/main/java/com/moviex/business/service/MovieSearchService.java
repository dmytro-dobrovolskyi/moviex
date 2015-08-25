package com.moviex.business.service;

import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ExecutionException;

@Service
public interface MovieSearchService {

    Set<MovieSearchMetadata> findByTitle(String title) throws InterruptedException, ExecutionException;
}
