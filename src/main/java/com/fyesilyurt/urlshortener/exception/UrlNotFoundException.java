package com.fyesilyurt.urlshortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UrlNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1920220197411266366L;

    public UrlNotFoundException(String message) {
        super(message);
    }
}
