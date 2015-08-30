package com.moviex.business.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviex.business.service.MovieService;
import com.moviex.business.service.util.ObjectMapperUtil;
import com.moviex.persistence.entity.movie.Movie;
import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import com.moviex.persistence.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Async
    @Override
    public void upsertAsync(Iterable<Movie> movies) {
        upsert(movies);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void upsert(Iterable<Movie> movies) {
        movieRepository.save(movies);
    }

    @Async
    @Override
    public void processUnmappedMovies(List<String> unmappedResultList, List<MovieSearchMetadata> searchMetadataList) {
        List<Movie> movies = unmappedResultList
                .stream()
                .map(unmappedResult -> ObjectMapperUtil.mapFromString(unmappedResult, Movie.class))
                .collect(Collectors.toList());

        IntStream
                .range(0, movies.size())
                .forEach(index -> {
                    Movie movie = movies.get(index);
                    MovieSearchMetadata searchMetadata = searchMetadataList.get(index);

                    movie.setMovieSearchMetadata(searchMetadata);
                    searchMetadata.setMovie(movie);
                });
        upsert(movies);
    }
}
