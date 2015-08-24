package com.moviex.presentation.advice;

import com.moviex.business.exception.MovieRequestFailedException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MovieControllerAdvice {

    @ResponseBody
    @ExceptionHandler(MovieRequestFailedException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    VndErrors userNotFoundExceptionHandler(MovieRequestFailedException ex) {
        return new VndErrors("error", ex.getMessage());
    }
}
