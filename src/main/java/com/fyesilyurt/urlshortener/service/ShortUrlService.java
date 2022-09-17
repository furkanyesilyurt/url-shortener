package com.fyesilyurt.urlshortener.service;

import com.fyesilyurt.urlshortener.converter.ShortUrlConverter;
import com.fyesilyurt.urlshortener.converter.ShortUrlRequestConverter;
import com.fyesilyurt.urlshortener.dto.ShortUrlDto;
import com.fyesilyurt.urlshortener.dto.ShortUrlRequestDto;
import com.fyesilyurt.urlshortener.entity.ShortUrl;
import com.fyesilyurt.urlshortener.exception.CodeAlreadyExistsException;
import com.fyesilyurt.urlshortener.exception.NotFoundAnyUrlException;
import com.fyesilyurt.urlshortener.exception.UrlCanNotEmptyException;
import com.fyesilyurt.urlshortener.exception.UrlNotFoundException;
import com.fyesilyurt.urlshortener.repository.ShortUrlRepository;
import com.fyesilyurt.urlshortener.util.RandomStringGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
        List<ShortUrlDto> shortUrlDtos = shortUrlConverter.convertShortUrlListToShortUrlDtoList(shortUrlRepository.findAll());
        if (CollectionUtils.isEmpty(shortUrlDtos)) {
            throw new NotFoundAnyUrlException("No registered urls could not to be found.");
        }
        return shortUrlConverter.convertShortUrlListToShortUrlDtoList(shortUrlRepository.findAll());
    }

    public ShortUrlDto getUrlByCode(String code) {
        ShortUrl shortUrl = shortUrlRepository.findShortUrlByCode(code)
                .orElseThrow(() -> new UrlNotFoundException("URL with " + code + " code could not to be found"));
        return shortUrlConverter.convertShortUrlToShortUrlDto(shortUrl);
    }

    public ShortUrlDto create(ShortUrlRequestDto shortUrlRequestDto) {
        if (!StringUtils.hasLength(shortUrlRequestDto.getUrl())) {
            throw new UrlCanNotEmptyException("Url must not empty");
        }

        ShortUrl shortUrl = shortUrlRequestConverter.convertToEntity(shortUrlRequestDto);
        String resultCode = shortUrl.getCode();
        if (!StringUtils.hasLength(resultCode)) {
            resultCode = generateCode();
        } else if (shortUrlRepository.findShortUrlByCode(resultCode.toUpperCase()).isPresent()) {
            throw new CodeAlreadyExistsException("Code (" + resultCode + ") already exists.");
        }
        shortUrl.setCode(resultCode.toUpperCase());
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
