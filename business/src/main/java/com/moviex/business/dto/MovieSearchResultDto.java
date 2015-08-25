package com.moviex.business.dto;

import com.moviex.persistence.entity.movie.MovieSearchMetadata;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieSearchResultDto {

    private List<MovieSearchMetadata> searchResult;
    private Boolean isRequestRequired;

    public MovieSearchResultDto(List<MovieSearchMetadata> searchResult, Boolean isRequestRequired) {
        this.searchResult = searchResult;
        this.isRequestRequired = isRequestRequired;
    }
}
