package com.moviex.business.service.impl;

import com.moviex.business.dto.movie.MovieSearchResultDto;
import com.moviex.business.dto.movie.MovieSearchResultInfo;
import com.moviex.business.service.MovieSearchService;
import com.moviex.business.service.MovieService;
import com.moviex.persistence.entity.movie.Movie;
import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import com.moviex.persistence.repository.MovieSearchMetadataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieSearchServiceImpl implements MovieSearchService {

    private static final Logger logger = LoggerFactory.getLogger(MovieSearchServiceImpl.class);

    @Autowired
    private MovieSearchMetadataRepository movieSearchMetadataRepository;

    @Autowired
    private MovieService movieService;

    @Override
    @Transactional(readOnly = true)
    public MovieSearchResultDto findByTitle(String title, Boolean isForce) {

        List<MovieSearchMetadata> searchResult = movieSearchMetadataRepository.findByTitleContainingIgnoreCase(title);
        MovieSearchResultInfo resultInfo = MovieSearchResultInfo.OK;

        if (searchResult.isEmpty() || isForce) {
            Set<Movie> movies = movieService.requestByTitle(title);
            searchResult.addAll(
                    movies
                            .stream()
                            .map(Movie::getMovieSearchMetadata)
                            .collect(Collectors.toList())
            );
            upsertAsync(movies);
            resultInfo = MovieSearchResultInfo.BY_WORD_REQUEST_REQUIRED;
        }
        return new MovieSearchResultDto(searchResult, resultInfo);
    }

    @Async
    public void upsertAsync(Iterable<Movie> movies) {
        movieService.upsert(movies);
    }
}
