package com.moviex.persistence.repository;

import com.moviex.persistence.entity.movie.MovieSearchMetadata;

import java.util.List;

public interface MovieSearchMetadataRepositoryCustom {
    List<MovieSearchMetadata> searchByTitle(String title, boolean isAdvanced);
}
