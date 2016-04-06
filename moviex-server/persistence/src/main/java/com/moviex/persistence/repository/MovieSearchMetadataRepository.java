package com.moviex.persistence.repository;

import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "movie-metadata", collectionResourceRel = "searchMetadata")
public interface MovieSearchMetadataRepository extends CrudRepository<MovieSearchMetadata, String> {
    List<MovieSearchMetadata> findByTitleContainingIgnoreCase(String title);
}
