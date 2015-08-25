package com.moviex.persistence.repository;

import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "search-metadata", path = "search-metadata")
public interface MovieSearchMetadataRepository extends CrudRepository<MovieSearchMetadata, String> {

    List<MovieSearchMetadata> findByTitleContainingIgnoreCase(String title);
}
