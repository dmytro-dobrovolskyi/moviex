package com.moviex.presentation.controller.movie;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

@RepositoryRestController
@RequestMapping(value = "movie")
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private static List<String> exceptions = Arrays.asList("a", "the", "of", "is", "are", "was", "were");

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/request-and-save", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void retrieveAndSave(@RequestBody List<String> titleWords) {

        logger.warn("--------------");
        long start = System.currentTimeMillis();

        List<Future<? extends Iterable<Movie>>> futureMovieList = new ArrayList<>();

        titleWords
                .stream()
                .filter(word -> !exceptions.contains(word))
                .forEach(word -> futureMovieList.add(movieService.requestByTitleAsync(word)));

        movieService.upsertWhenReady(futureMovieList);

        System.out.println(System.currentTimeMillis() - start);
        logger.warn("--------------");
    }
}
