package com.moviex.business.service;

import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieSearchService {

    List<MovieSearchMetadata> findByTitleSmartly(String title);

    List<MovieSearchMetadata> findByTitle(String title);

    List<MovieSearchMetadata> findByTitleOnImdb(String title);
}
