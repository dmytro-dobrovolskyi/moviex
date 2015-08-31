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

import java.util.List;
import java.util.stream.Collectors;

@RepositoryRestController
@RequestMapping(value = "/movie/search")
public class MovieSearchController {

    private static final Logger logger = LoggerFactory.getLogger(MovieSearchController.class);

    @Autowired
    private MovieSearchService movieSearchService;

    @Autowired
    @Qualifier(value = "entityLinks")   //RepositoryEntityLinks injects here
    private EntityLinks entityLinks;

    @RequestMapping(value = "/by-title")
    public
    @ResponseBody
    Resources<Resource> findByTitle(@RequestParam String title, @RequestParam Boolean isForce) {
        return toMovieResources(movieSearchService.findByTitle(title, isForce));
    }

    @RequestMapping(value = "/advanced/by-title")
    public
    @ResponseBody
    Resources<Resource> findByTitle(@RequestParam String title) {
        return toMovieResources(movieSearchService.findByTitle(title));
    }

    private Resources<Resource> toMovieResources(List<MovieSearchMetadata> movieMetadataList) {
        return new Resources<Resource>(
                movieMetadataList
                        .stream()
                        .map(searchMetadata -> new Resource<MovieSearchMetadata>(
                                        searchMetadata,
                                        entityLinks.linkToSingleResource(MovieSearchMetadataRepository.class, searchMetadata.getImdbID()).withSelfRel(),
                                        entityLinks.linkToSingleResource(MovieRepository.class, searchMetadata.getImdbID()).withRel("movie")
                                )
                        )
                        .collect(Collectors.toList()));
    }
}
