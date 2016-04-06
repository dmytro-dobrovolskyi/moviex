package com.moviex.business.service.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moviex.business.service.MovieSearchService;
import com.moviex.business.service.MovieService;
import com.moviex.business.util.Loggable;
import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import com.moviex.persistence.repository.MovieSearchMetadataRepository;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieSearchServiceImpl implements MovieSearchService, Loggable {
    private static final Logger logger = LoggerFactory.getLogger(MovieSearchServiceImpl.class);
    public static final String IMDB_URL = "http://www.omdbapi.com";

    @Autowired
    private MovieSearchMetadataRepository movieSearchMetadataRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RestOperations restOperations;

    @Override
    @Transactional(readOnly = true)
    public List<MovieSearchMetadata> findByTitle(String title) {
        List<MovieSearchMetadata> searchResult = movieSearchMetadataRepository.findByTitleContainingIgnoreCase(title);

        if (searchResult.isEmpty()) {
            searchResult = findByTitleOnImdb(title);
        }
        return searchResult;
    }

    @Override
    public List<MovieSearchMetadata> findByTitleOnImdb(String title) {
        RequestResultHolder searchRequestResult = restOperations.getForObject(IMDB_URL + "/?s=" + title, RequestResultHolder.class);

        List<MovieSearchMetadata> movieSearchMetadataList = new ArrayList<>();

        if (searchRequestResult.isSuccess) {
             movieSearchMetadataList.addAll(searchRequestResult.getMovieSearchMetadataList());
             movieService.loadMoviesBasedOnMetadata(movieSearchMetadataList);
        }
        return movieSearchMetadataList;
    }

    @Override
    public List<MovieSearchMetadata> findByTitleSmartly(String title) {
        // TODO
        return findByTitle(title);
    }

    @Getter
    @Setter
    private static class RequestResultHolder {
        @JsonProperty("Search")
        private List<MovieSearchMetadata>  movieSearchMetadataList;

        @JsonProperty("Response")
        private Boolean isSuccess = true;
    }
}
