package com.fyesilyurt.urlshortener.service;

import com.fyesilyurt.urlshortener.converter.ShortUrlConverter;
import com.fyesilyurt.urlshortener.converter.ShortUrlRequestConverter;
import com.fyesilyurt.urlshortener.dto.ShortUrlDto;
import com.fyesilyurt.urlshortener.dto.ShortUrlRequestDto;
import com.fyesilyurt.urlshortener.entity.ShortUrl;
import com.fyesilyurt.urlshortener.exception.CodeAlreadyExistsException;
import com.fyesilyurt.urlshortener.exception.UrlNotFoundException;
import com.fyesilyurt.urlshortener.repository.ShortUrlRepository;
import com.fyesilyurt.urlshortener.util.RandomStringGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShortUrlService {

    private final ShortUrlRepository shortUrlRepository;
    private final ShortUrlConverter shortUrlConverter;
    private final ShortUrlRequestConverter shortUrlRequestConverter;
    private final RandomStringGenerator stringGenerator;


    public List<ShortUrlDto> getAllUrls() {
        return shortUrlConverter.convertShortUrlListToShortUrlDtoList(shortUrlRepository.findAll());
    }

    public ShortUrlDto getUrlByCode(String code) {
        ShortUrl shortUrl = shortUrlRepository.findShortUrlByCode(code)
                .orElseThrow(() -> new UrlNotFoundException("URL with " + code + " code could not to be found"));
        return shortUrlConverter.convertShortUrlToShortUrlDto(shortUrl);
    }

    public ShortUrlDto create(ShortUrlRequestDto shortUrlRequestDto) {
        ShortUrl shortUrl = shortUrlRequestConverter.convertToEntity(shortUrlRequestDto);
        String requestCode = shortUrl.getCode();
        if (!StringUtils.hasLength(requestCode)) {
            shortUrl.setCode(generateCode());
        } else if (shortUrlRepository.findShortUrlByCode(shortUrl.getCode()).isPresent()) {
            throw new CodeAlreadyExistsException("Code(" + requestCode + ") already exists.");
        }
        return shortUrlConverter.convertShortUrlToShortUrlDto(shortUrlRepository.save(shortUrl));
    }

    private String generateCode() {
        String code;
        do {
            code = stringGenerator.generateRandomString();
        } while (shortUrlRepository.findShortUrlByCode(code).isPresent());
        return code;
    }
}
