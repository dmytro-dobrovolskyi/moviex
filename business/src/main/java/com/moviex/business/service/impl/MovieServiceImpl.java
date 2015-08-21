package com.moviex.business.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.moviex.business.config.BusinessConfig;
import com.moviex.business.service.MovieService;
import com.moviex.persistence.entity.movie.Movie;
import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import com.moviex.persistence.repository.MovieRepository;
import com.moviex.persistence.repository.MovieSearchMetadataRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private static final String IMDB_URL = "http://www.omdbapi.com";

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieSearchMetadataRepository movieSearchMetadataRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Transactional(readOnly = true)
    public Movie findById(String id) {
        return movieRepository.findOne(id);
    }

    @Override
    @Transactional
    public List<MovieSearchMetadata> findByTitle(String title) {
        List<MovieSearchMetadata> searchResult = movieSearchMetadataRepository.findByTitleContainingIgnoreCase(title);

        if (searchResult.isEmpty()) {
            List<Movie> movies = restTemplate
                    .getForObject(IMDB_URL + "/?s=" + title, MovieSearchMetadataHolder.class)
                    .searchMetadata
                    .stream()
                    .map(movieMetadata -> {
                                Movie movie = restTemplate.getForObject(IMDB_URL + "/?i=" + movieMetadata.getImdbID(), Movie.class);
                                movie.setMovieSearchMetadata(movieMetadata);
                                movieMetadata.setMovie(movie);
                                return movie;
                            }
                    )
                    .collect(Collectors.toList());

            movies.forEach(System.out::println);
            movieRepository.save(movies);
            searchResult = movieSearchMetadataRepository.findByTitleContainingIgnoreCase(title);
        }
        return searchResult;
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

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(BusinessConfig.class).getBean(MovieService.class)
                .findByTitle("Game of Thrones")
                .forEach(System.out::println);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    private static class MovieSearchMetadataHolder {

        private List<MovieSearchMetadata> searchMetadata;

        @JsonProperty("Search")
        public void setSearch(List<MovieSearchMetadata> search) {
            this.searchMetadata = search;
        }
    }
}
