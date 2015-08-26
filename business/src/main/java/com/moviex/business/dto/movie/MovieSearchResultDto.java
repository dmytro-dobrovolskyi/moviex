package com.moviex.business.dto.movie;

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
    private MovieSearchResultInfo resultInfo;

    public MovieSearchResultDto(List<MovieSearchMetadata> searchResult, MovieSearchResultInfo resultInfo) {
        this.searchResult = searchResult;
        this.resultInfo = resultInfo;
    }
}
