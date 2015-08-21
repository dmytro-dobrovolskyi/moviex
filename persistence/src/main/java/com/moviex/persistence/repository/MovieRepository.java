package com.moviex.persistence.repository;

import com.moviex.persistence.entity.movie.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "movies", path = "movie")
public interface MovieRepository extends CrudRepository<Movie, String> {

    @RestResource(path = "by-title")
    List<Movie> findByMovieSearchMetadataTitleContainingIgnoreCase(@Param("title") String title);
}
