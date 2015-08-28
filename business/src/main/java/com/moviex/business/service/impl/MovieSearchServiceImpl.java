package com.moviex.business.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviex.business.exception.MovieRequestFailedException;
import com.moviex.business.service.MovieSearchService;
import com.moviex.business.service.MovieService;
import com.moviex.persistence.entity.movie.Movie;
import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import com.moviex.persistence.repository.MovieSearchMetadataRepository;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MovieSearchServiceImpl implements MovieSearchService {

    private static final Logger logger = LoggerFactory.getLogger(MovieSearchServiceImpl.class);
    private static final String IMDB_URL = "http://www.omdbapi.com";

    @Autowired
    private MovieSearchMetadataRepository movieSearchMetadataRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MovieSearchMetadata> findByTitle(String title, Boolean isForce) {

        List<MovieSearchMetadata> searchResult;

        if (isForce || (searchResult = movieSearchMetadataRepository.findByTitleContainingIgnoreCase(title)).isEmpty()) {
            searchResult = requestByTitle(title);
        }
        return searchResult;
    }


    @Override
    public List<MovieSearchMetadata> requestByTitle(String title) {

        RequestResultHolder searchRequestResult = restTemplate.getForObject(IMDB_URL + "/?s=" + title, RequestResultHolder.class);

        List<MovieSearchMetadata> searchMetadataList = new ArrayList<>();

        if (searchRequestResult.isSuccess) {
            List<String> unmappedResultList = new ArrayList<>();

            searchMetadataList.addAll(
                    searchRequestResult
                            .imdbIdList
                            .stream()
                            .map(imdbId -> {
                                logger.warn("===========");
                                String requestByIdResult = restTemplate.getForObject(IMDB_URL + "/?plot=full&i=" + imdbId, String.class);
                                unmappedResultList.add(requestByIdResult);
                                return mapFromString(requestByIdResult, MovieSearchMetadata.class);
                            })
                            .collect(Collectors.toList())
            );
            logger.warn("-----BEFORE-------");
            processMovies(unmappedResultList, searchMetadataList);
            logger.warn("-----AFTER-------");
        }
        return searchMetadataList;
    }

    private <T> T mapFromString(String dataToMap, Class<? extends T> mappedObjClass) {
        try {
            return objectMapper.readValue(dataToMap, mappedObjClass);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            throw new MovieRequestFailedException(dataToMap);
        }
    }

    @Async
    public void processMovies(List<String> unmappedResultList, List<MovieSearchMetadata> searchMetadataList) {
        List<Movie> movies = unmappedResultList
                .stream()
                .map(unmappedResult -> mapFromString(unmappedResult, Movie.class))
                .collect(Collectors.toList());

        IntStream
                .range(0, movies.size())
                .forEach(index -> {
                    Movie movie = movies.get(index);
                    MovieSearchMetadata searchMetadata = searchMetadataList.get(index);

                    movie.setMovieSearchMetadata(searchMetadata);
                    searchMetadata.setMovie(movie);
                });
        movieService.upsert(movies);
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
