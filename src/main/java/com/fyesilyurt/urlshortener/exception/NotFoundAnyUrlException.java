package com.fyesilyurt.urlshortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundAnyUrlException extends RuntimeException{

    private static final long serialVersionUID = -7716493885072458873L;

    public NotFoundAnyUrlException(String message) {
        super(message);
    }
}
