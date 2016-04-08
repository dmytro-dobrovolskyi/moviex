package com.moviex.imgservice.exception;

public class NoTokenProvidedException extends RuntimeException {
    public NoTokenProvidedException() {
        super("You must provide access token to access your Google Drive account");
    }
}
