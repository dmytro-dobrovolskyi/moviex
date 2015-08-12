package com.moviex.business.service.impl;

import com.moviex.business.service.MovieService;
import com.moviex.persistence.entity.Movie;
import com.moviex.persistence.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    @Transactional(readOnly = true)
    public Movie findById(String id) {
        return movieRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    @Transactional
    public void upsert(Movie movie) {
        movieRepository.save(movie);
    }


}
