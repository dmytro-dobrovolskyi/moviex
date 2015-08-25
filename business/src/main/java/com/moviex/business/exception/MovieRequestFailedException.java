package com.moviex.business.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MovieRequestFailedException extends RuntimeException {

    public MovieRequestFailedException(String message) {
        super("Could not retrieve movie: " + message);
    }
}
