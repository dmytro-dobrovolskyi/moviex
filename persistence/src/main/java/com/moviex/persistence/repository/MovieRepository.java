package com.moviex.persistence.repository;

import com.moviex.persistence.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface MovieRepository extends CrudRepository<Movie, String> {}
