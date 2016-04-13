package com.moviex.business.service.impl;

import com.moviex.business.service.MovieService;
import com.moviex.persistence.entity.movie.Movie;
import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import com.moviex.persistence.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestOperations;

import java.util.List;
import java.util.stream.Collectors;

import static com.moviex.business.service.impl.DefaultMovieSearchService.IMDB_URL;

@Service
public class DefaultMovieService implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RestOperations restOperations;

    @Override
    @Transactional(readOnly = true)
    public Movie findById(String id) {
        return movieRepository.findOne(id);
    }

    @Override
    @Transactional
    public void upsert(Movie movie) {
        movieRepository.save(movie);
    }

    @Async
    @Override
    public void upsertAsync(Iterable<Movie> movies) {
        upsert(movies);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void upsert(Iterable<Movie> movies) {
        movieRepository.save(movies);
    }

    @Async
    @Override
    public void loadMoviesBasedOnMetadata(List<MovieSearchMetadata> searchMetadataList) {
        List<Movie> movies = searchMetadataList.parallelStream()
                .map(metadata -> {
                    Movie movie = restOperations.getForObject(
                            IMDB_URL + "/?plot=full&i=" + metadata.getImdbID(),
                            Movie.class);
                    metadata.setMovie(movie);
                    movie.setMovieSearchMetadata(metadata);

                    return movie;
                })
                .collect(Collectors.toList());
        upsert(movies);
    }
}
