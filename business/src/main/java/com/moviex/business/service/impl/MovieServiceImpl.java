package com.moviex.business.service.impl;

import com.moviex.business.service.MovieService;
import com.moviex.persistence.entity.movie.Movie;
import com.moviex.persistence.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private MovieRepository movieRepository;

    @Override
    @Transactional(readOnly = true)
    public Movie findById(String id) {
        return movieRepository.findOne(id);
    }

    @Override
    @Transactional
    public void upsert(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    @Transactional
    public void batchUpsert(List<Movie> movie) {
        movieRepository.save(movie);
    }
}
