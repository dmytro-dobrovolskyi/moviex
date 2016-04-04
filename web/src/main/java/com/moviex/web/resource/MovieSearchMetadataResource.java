package com.moviex.web.resource;

import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

@Getter
public class MovieSearchMetadataResource extends ResourceSupport {
    private final MovieSearchMetadata searchMetadata;

    public MovieSearchMetadataResource(MovieSearchMetadata searchMetadata) {
        this.searchMetadata = searchMetadata;
    }
}
