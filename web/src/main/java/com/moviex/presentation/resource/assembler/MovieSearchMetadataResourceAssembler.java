package com.moviex.presentation.resource.assembler;

import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import com.moviex.presentation.resource.MovieSearchMetadataResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class MovieSearchMetadataResourceAssembler extends ResourceAssemblerSupport<MovieSearchMetadata, MovieSearchMetadataResource> {

    @Autowired
    private EntityLinks entityLinks;

    public MovieSearchMetadataResourceAssembler() {
        super(MovieSearchMetadata.class, MovieSearchMetadataResource.class);
    }

    @Override
    public MovieSearchMetadataResource toResource(MovieSearchMetadata searchMetadata) {

        return null;
    }
}
