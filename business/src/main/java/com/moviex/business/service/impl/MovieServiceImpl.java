package com.moviex.business.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviex.business.exception.MovieRequestFailedException;
import com.moviex.business.service.MovieService;
import com.moviex.persistence.entity.movie.Movie;
import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import com.moviex.persistence.repository.MovieRepository;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);
    private static final String IMDB_URL = "http://www.omdbapi.com";

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RestTemplate restTemplate;

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

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void upsert(Iterable<Movie> movies) {
        movieRepository.save(movies);
    }


    @Override
    public Set<Movie> requestByTitle(String title) {
        RequestResultHolder requestResult = restTemplate.getForObject(IMDB_URL + "/?s=" + title, RequestResultHolder.class);

        Set<Movie> movies = new HashSet<>();

        if (requestResult.isSuccess) {
            movies.addAll(
                    requestResult.imdbIdList
                            .stream()
                            .map(retrieveByIdAndMap())
                            .collect(Collectors.toCollection(HashSet::new))
            );
        }
        return movies;
    }

    @Override
    @Async
    public Future<Set<Movie>> requestByTitleAsync(String title) {
        return new AsyncResult<>(requestByTitle(title));
    }

    private Function<ImdbIdHolder, Movie> retrieveByIdAndMap() {
        return imdbId -> {
            String requestResult = restTemplate.getForObject(IMDB_URL + "/?i=" + imdbId, String.class);

            Movie movie = mapFromString(requestResult, Movie.class);
            MovieSearchMetadata searchMetadata = mapFromString(requestResult, MovieSearchMetadata.class);

            movie.setMovieSearchMetadata(searchMetadata);
            searchMetadata.setMovie(movie);

            return movie;
        };
    }

    private <T> T mapFromString(String dataToMap, Class<? extends T> mappedObjClass) {
        try {
            return objectMapper.readValue(dataToMap, mappedObjClass);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            throw new MovieRequestFailedException(dataToMap);
        }
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ImdbIdHolder {
        private String imdbID;

        @Override
        public String toString() {
            return imdbID;
        }
    }


    @Getter
    @Setter
    private static class RequestResultHolder {

        @JsonProperty("Search")
        private List<ImdbIdHolder> imdbIdList;

        @JsonProperty("Response")
        private Boolean isSuccess = true;
    }
}
