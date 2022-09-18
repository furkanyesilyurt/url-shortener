package com.fyesilyurt.urlshortener.config;

import com.fyesilyurt.urlshortener.entity.ShortUrl;
import com.fyesilyurt.urlshortener.helper.DataCreater;
import com.fyesilyurt.urlshortener.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class InitConfig {

    private final ShortUrlRepository repository;

    @PostConstruct
    public void onInit() {
        List<ShortUrl> shortUrls = DataCreater.createUrls();
        for (ShortUrl shortUrl : shortUrls) {
            if (!repository.findShortUrlByCode(shortUrl.getCode()).isPresent()) {
                repository.save(shortUrl);
            }
        }
    }
}
