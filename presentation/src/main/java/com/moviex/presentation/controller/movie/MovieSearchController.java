package com.moviex.presentation.controller.movie;

import com.moviex.business.service.MovieSearchService;
import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import com.moviex.persistence.repository.MovieRepository;
import com.moviex.persistence.repository.MovieSearchMetadataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RepositoryRestController
@RequestMapping(value = "/movie/search")
public class MovieSearchController {

    private static final Logger logger = LoggerFactory.getLogger(MovieSearchController.class);

    @Autowired
    private MovieSearchService movieSearchService;

    @Autowired
    @Qualifier(value = "entityLinks")   //RepositoryEntityLinks injects here
            EntityLinks entityLinks;

    @RequestMapping(value = "/by-title")
    public
    @ResponseBody
    Resources<Resource> findByTitle(@RequestParam String title) throws ExecutionException, InterruptedException {

        return new Resources<Resource>(
                movieSearchService
                        .findByTitle(title)
                        .stream()
                        .map(movie -> new Resource<MovieSearchMetadata>(
                                        movie,
                                        entityLinks.linkToSingleResource(MovieSearchMetadataRepository.class, movie.getImdbID()).withSelfRel(),
                                        entityLinks.linkToSingleResource(MovieRepository.class, movie.getImdbID()).withRel("movie")
                                )
                        )
                        .collect(Collectors.toList())
        );
    }
}
