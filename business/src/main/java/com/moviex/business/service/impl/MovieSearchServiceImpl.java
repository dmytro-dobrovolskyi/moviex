package com.moviex.business.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.moviex.business.service.MovieSearchService;
import com.moviex.business.service.MovieService;
import com.moviex.business.service.util.ObjectMapperUtil;
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
import java.util.stream.Collectors;

@Service
public class MovieSearchServiceImpl implements MovieSearchService {
    private static final Logger logger = LoggerFactory.getLogger(MovieSearchServiceImpl.class);
    private static final String IMDB_URL = "http://www.omdbapi.com";

    @Autowired
    private MovieSearchMetadataRepository movieSearchMetadataRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RestOperations restOperations;

    @Override
    @Transactional(readOnly = true)
    public List<MovieSearchMetadata> findByTitle(String title, Boolean isForce) {
        List<MovieSearchMetadata> searchResult;

        if (isForce || (searchResult = movieSearchMetadataRepository.searchByTitle(title, false)).isEmpty()) {
            searchResult = requestByTitle(title);
        }
        return searchResult;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieSearchMetadata> findByTitle(String title) {
        return movieSearchMetadataRepository.searchByTitle(title, true);
    }

    @Override
    public List<MovieSearchMetadata> requestByTitle(String title) {
        RequestResultHolder searchRequestResult = restOperations.getForObject(IMDB_URL + "/?s=" + title, RequestResultHolder.class);

        List<MovieSearchMetadata> searchMetadataList = new ArrayList<>();

        if (searchRequestResult.isSuccess) {
            List<String> unmappedResultList = new ArrayList<>();

            searchMetadataList.addAll(
                    searchRequestResult
                            .imdbIdList
                            .stream()
                            .map(imdbId -> {
                                String requestByIdResult = restOperations.getForObject(IMDB_URL + "/?plot=full&i=" + imdbId, String.class);
                                unmappedResultList.add(requestByIdResult);
                                return ObjectMapperUtil.mapFromString(requestByIdResult, MovieSearchMetadata.class);
                            })
                            .collect(Collectors.toList())
            );
            movieService.processUnmappedMovies(unmappedResultList, searchMetadataList);
        }
        return searchMetadataList;
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
