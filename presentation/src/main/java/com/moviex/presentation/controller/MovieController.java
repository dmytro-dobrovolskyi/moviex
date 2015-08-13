package com.moviex.presentation.controller;

import com.moviex.business.service.MovieService;
import com.moviex.persistence.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/{title}")
    public List<Movie> getByTitle(@PathVariable String title)
    {
        System.out.println("Hello");
        return movieService.findByTitle(title);
    }
}
