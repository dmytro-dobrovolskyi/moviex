package com.moviex.persistence.repository;

import com.moviex.persistence.entity.movie.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "movie", collectionResourceRel = "movies")
public interface MovieRepository extends CrudRepository<Movie, String> {

}
