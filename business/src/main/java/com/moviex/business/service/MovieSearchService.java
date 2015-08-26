package com.moviex.business.service;

import com.moviex.business.dto.movie.MovieSearchResultDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface MovieSearchService {

    MovieSearchResultDto findByTitle(String title, Boolean isForce);
}
