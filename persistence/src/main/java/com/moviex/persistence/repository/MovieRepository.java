package com.moviex.persistence.repository;

import com.moviex.persistence.entity.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, String>
{
    List<Movie> findByTitle(String title);
}
