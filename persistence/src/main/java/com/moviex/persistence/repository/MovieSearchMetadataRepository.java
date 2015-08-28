package com.moviex.persistence.repository;

import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieSearchMetadataRepository extends CrudRepository<MovieSearchMetadata, String> {

    List<MovieSearchMetadata> findByTitleContainingIgnoreCase(String title);
}
