package com.moviex.presentation.controller.movie;

import com.moviex.business.exception.MovieRequestFailedException;
import com.moviex.business.service.MovieService;
import com.moviex.persistence.entity.movie.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RepositoryRestController
@RequestMapping(value = "movie")
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private static final List<String> EXCLUSIONS = Collections.unmodifiableList(
            Arrays.asList("a", "the", "of", "is", "are", "was", "were"));

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/persistence/request/by-word", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void retrieveAndSave(@RequestBody List<String> titleWords) {

        List<Future<Set<Movie>>> futureMovies =
                titleWords
                        .stream()
                        .filter(word -> !EXCLUSIONS.contains(word))
                        .map(movieService::requestByTitleAsync)
                        .collect(Collectors.toList());

        movieService.upsert(
                futureMovies
                        .stream()
                        .flatMap(mergeMovies())
                        .collect(Collectors.toSet())
        );
    }

    private Function<Future<Set<Movie>>, Stream<Movie>> mergeMovies() {
        return futureMovies -> {
            try {
                return futureMovies.get().stream();
            } catch (InterruptedException | ExecutionException ex) {
                String msg = ex.getMessage();
                logger.error(msg);
                throw new MovieRequestFailedException(msg);
            }
        };
    }
}
