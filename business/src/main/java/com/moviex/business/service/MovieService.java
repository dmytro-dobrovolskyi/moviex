package com.moviex.business.service;

import com.moviex.persistence.entity.Movie;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {

    Movie findById(String id);

    void upsert(Movie movie);
}
