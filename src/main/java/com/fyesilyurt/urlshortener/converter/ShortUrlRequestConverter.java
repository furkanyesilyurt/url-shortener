package com.fyesilyurt.urlshortener.converter;

import com.fyesilyurt.urlshortener.dto.ShortUrlRequestDto;
import com.fyesilyurt.urlshortener.entity.ShortUrl;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlRequestConverter {

    public ShortUrl convertToEntity(ShortUrlRequestDto shortUrlRequest) {
        return ShortUrl.builder()
                .url(shortUrlRequest.getUrl())
                .code(shortUrlRequest.getCode())
                .build();
    }
}
