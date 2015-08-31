package com.moviex.persistence.repository;

import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "searchMetadata", path = "movie-metadata")
public interface MovieSearchMetadataRepository extends CrudRepository<MovieSearchMetadata, String>, MovieSearchMetadataRepositoryCustom {}
