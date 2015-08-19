package com.moviex.persistence.repository;

import com.moviex.persistence.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "movies", path = "movie")
public interface MovieRepository extends CrudRepository<Movie, String> {

    List<Movie> findByTitle(@Param("title") String title);
}
