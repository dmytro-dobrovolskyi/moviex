package com.moviex.business.exception;

public class MovieRequestFailedException extends RuntimeException {

    public MovieRequestFailedException(String content) {
        super("Could not retrieve movie: " + content);
    }
}
