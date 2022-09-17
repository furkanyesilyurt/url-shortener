package com.fyesilyurt.urlshortener.exception;

import com.fyesilyurt.urlshortener.dto.ShortUrlResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.fyesilyurt.urlshortener.exhibition.ShortUrlConstants.INTERNAL_SERVER_ERROR_MESSAGE_KEY;
import static com.fyesilyurt.urlshortener.exhibition.ShortUrlConstants.URL_SHORTENER_PREFIX;

@Component
public class ShortUrlExceptionHandler {

    public ShortUrlResponseDto handle(Exception ex) {
        String message;
        if(ex instanceof UrlNotFoundException) {
            UrlNotFoundException exception = (UrlNotFoundException) ex;
            message = new StringBuilder().append(URL_SHORTENER_PREFIX).append(".").append(exception.getMessage()).toString();
        } else if (ex instanceof NotFoundAnyUrlException) {
            NotFoundAnyUrlException exception = (NotFoundAnyUrlException) ex;
            message = new StringBuilder().append(URL_SHORTENER_PREFIX).append(".").append(exception.getMessage()).toString();
        } else {
            message = new StringBuilder().append(INTERNAL_SERVER_ERROR_MESSAGE_KEY).append(".").append(ex.getMessage()).toString();
        }

        return ShortUrlResponseDto.builder()
                .message(message)
                .responseCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }
}
