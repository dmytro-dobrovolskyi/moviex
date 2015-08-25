package com.moviex.business.service;

import com.moviex.business.dto.MovieSearchResultDto;
import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public interface MovieSearchService {

    MovieSearchResultDto findByTitle(String title);
}
