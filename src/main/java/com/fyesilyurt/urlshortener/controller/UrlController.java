package com.fyesilyurt.urlshortener.controller;

import com.fyesilyurt.urlshortener.dto.ShortUrlDto;
import com.fyesilyurt.urlshortener.dto.ShortUrlRequestDto;
import com.fyesilyurt.urlshortener.dto.ShortUrlResponseDto;
import com.fyesilyurt.urlshortener.exception.ShortUrlExceptionHandler;
import com.fyesilyurt.urlshortener.exception.UrlNotFoundException;
import com.fyesilyurt.urlshortener.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/shortUrl")
public class UrlController {

    private final ShortUrlService shortUrlService;
    private final ShortUrlExceptionHandler shortUrlExceptionHandler;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<ShortUrlResponseDto> getAllShortUrls(){
        ShortUrlResponseDto responseDto;
        try {
            responseDto = new ShortUrlResponseDto();
            responseDto.setShortUrlDtos(shortUrlService.getAllUrls());
        } catch (Exception exception) {
            responseDto = shortUrlExceptionHandler.handle(exception);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ResponseEntity<ShortUrlResponseDto> getUrlByCode(@Valid @NotEmpty @RequestParam String code) {
        ShortUrlResponseDto responseDto;
        try {
            responseDto = new ShortUrlResponseDto();
            responseDto.setShortUrlDtos(Arrays.asList(shortUrlService.getUrlByCode(code)));
        } catch (Exception exception) {
            responseDto = shortUrlExceptionHandler.handle(exception);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public ResponseEntity<ShortUrlResponseDto> redirect(@Valid @NotEmpty @RequestParam String code) throws URISyntaxException {
        ShortUrlDto shortUrl;
        try {
            shortUrl = shortUrlService.getUrlByCode(code);
        } catch (Exception exception) {
            throw new UrlNotFoundException(exception.getMessage());
        }
        URI uri = new URI(shortUrl.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ShortUrlResponseDto> create(@RequestBody ShortUrlRequestDto shortUrlRequestDto) {
        ShortUrlResponseDto responseDto;
        try {
            responseDto = new ShortUrlResponseDto();
            responseDto.setShortUrlDtos(Arrays.asList(shortUrlService.create(shortUrlRequestDto)));
        } catch (Exception exception) {
            responseDto = shortUrlExceptionHandler.handle(exception);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
