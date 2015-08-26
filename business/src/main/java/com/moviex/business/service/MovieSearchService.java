package com.moviex.business.service;

import com.moviex.business.dto.movie.MovieSearchResultDto;
import org.springframework.stereotype.Service;

@Service
public interface MovieSearchService {

    MovieSearchResultDto findByTitle(String title);
}
