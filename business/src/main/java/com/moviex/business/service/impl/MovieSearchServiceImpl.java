package com.moviex.business.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviex.business.service.MovieSearchService;
import com.moviex.persistence.entity.movie.Movie;
import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import com.moviex.persistence.repository.MovieRepository;
import com.moviex.persistence.repository.MovieSearchMetadataRepository;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MovieSearchServiceImpl implements MovieSearchService {

    private static final Logger logger = LoggerFactory.getLogger(MovieSearchServiceImpl.class);
    private static final String IMDB_URL = "http://www.omdbapi.com";

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieSearchMetadataRepository movieSearchMetadataRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public Set<MovieSearchMetadata> findByTitle(String title) {

        Set<MovieSearchMetadata> searchResult = movieSearchMetadataRepository.findByTitleContainingIgnoreCase(title);

        if (searchResult.isEmpty()) {
            String titleMatchesRegex = "(?i).*" + title + ".*";

            Arrays
                    .stream(title.split(" "))
                    .forEach(retrieveAndSave(searchResult, titleMatchesRegex));
        }
        return searchResult;
    }

    public Consumer<String> retrieveAndSave(Set<MovieSearchMetadata> searchResult, String titleMatchesRegex) {
        return word -> {
            RequestResultHolder requestResult = restTemplate.getForObject(IMDB_URL + "/?s=" + word, RequestResultHolder.class);

            if (requestResult.isSuccess) {
                movieRepository.save(
                        requestResult.imdbIdList
                                .stream()
                                .map(getAndMap(searchResult, titleMatchesRegex))
                                .collect(Collectors.toCollection(HashSet::new))
                );
            }
        };
    }

    private Function<ImdbIdHolder, Movie> getAndMap(Set<MovieSearchMetadata> searchResult, String titleMatchesRegex) {
        return imdbId -> {
            String requestResult = restTemplate.getForObject(IMDB_URL + "/?i=" + imdbId, String.class);

            Movie movie = mapFromString(requestResult, Movie.class);
            MovieSearchMetadata searchMetadata = mapFromString(requestResult, MovieSearchMetadata.class);

            movie.setMovieSearchMetadata(searchMetadata);
            searchMetadata.setMovie(movie);

            if (searchMetadata.getTitle().matches(titleMatchesRegex)) {
                searchResult.add(searchMetadata);
            }
            return movie;
        };
    }

    private <T> T mapFromString(String dataToMap, Class<? extends T> mappedObjClass) {
        try {
            return objectMapper.readValue(dataToMap, mappedObjClass);
        } catch (IOException ex) {
            logger.debug(ex.getMessage());
            throw new AssertionError(ex);
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
