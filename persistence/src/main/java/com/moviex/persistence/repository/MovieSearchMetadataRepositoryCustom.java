package com.moviex.persistence.repository;

import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieSearchMetadataRepositoryCustom {
    List<MovieSearchMetadata> searchByTitle(String title, boolean isAdvanced);
}
