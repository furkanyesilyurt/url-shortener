package com.fyesilyurt.urlshortener.converter;

import com.fyesilyurt.urlshortener.dto.ShortUrlDto;
import com.fyesilyurt.urlshortener.entity.ShortUrl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShortUrlConverter {

    public ShortUrlDto convertShortUrlToShortUrlDto(ShortUrl shortUrl) {
        return ShortUrlDto.builder()
                .id(shortUrl.getId())
                .url(shortUrl.getUrl())
                .code(shortUrl.getCode())
                .build();
    }

    public List<ShortUrlDto> convertShortUrlListToShortUrlDtoList(List<ShortUrl> shortUrlList) {
        return shortUrlList.stream()
                .map(this::convertShortUrlToShortUrlDto)
                .collect(Collectors.toList());
    }
}
