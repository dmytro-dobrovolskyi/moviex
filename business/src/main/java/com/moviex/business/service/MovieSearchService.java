package com.moviex.business.service;

import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieSearchService {

    List<MovieSearchMetadata> findByTitle(String title, Boolean isForce);

    List<MovieSearchMetadata> requestByTitle(String title);
}
