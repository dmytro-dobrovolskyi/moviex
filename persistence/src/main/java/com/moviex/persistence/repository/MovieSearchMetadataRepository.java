package com.moviex.persistence.repository;

import com.moviex.persistence.entity.movie.Movie;
import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieSearchMetadataRepository extends CrudRepository<MovieSearchMetadata, String> {

    List<MovieSearchMetadata> findByTitleContainingIgnoreCase(String title);
}
