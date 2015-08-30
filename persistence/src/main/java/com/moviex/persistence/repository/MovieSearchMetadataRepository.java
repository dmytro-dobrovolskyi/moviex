package com.moviex.persistence.repository;

import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "searchMetadata", path = "movie-metadata")
public interface MovieSearchMetadataRepository extends CrudRepository<MovieSearchMetadata, String> {

    @RestResource(path = "by-title")
    @Transactional(readOnly = true)
    List<MovieSearchMetadata> findByTitleContainingIgnoreCase(@Param("title") String title);
}
