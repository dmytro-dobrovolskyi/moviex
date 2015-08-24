package com.moviex.business.service;

import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface MovieSearchService {

    Set<MovieSearchMetadata> findByTitle(String title);
}
