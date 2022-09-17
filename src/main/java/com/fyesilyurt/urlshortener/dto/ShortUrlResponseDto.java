package com.fyesilyurt.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShortUrlResponseDto {

    private List<ShortUrlDto> shortUrlDtos;
    private String message = "SUCCESS";
    private int responseCode = HttpStatus.OK.value();
}
