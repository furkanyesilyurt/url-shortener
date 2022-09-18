package com.fyesilyurt.urlshortener.helper;

import com.fyesilyurt.urlshortener.entity.ShortUrl;

import java.util.Arrays;
import java.util.List;

public class DataCreater {

    public DataCreater() {
        throw new UnsupportedOperationException();
    }

    public static List<ShortUrl> createUrls() {
        ShortUrl url1 = ShortUrl.builder()
                .url("https://github.com/furkanyesilyurt")
                .code("github")
                .build();

        ShortUrl url2 = ShortUrl.builder()
                .url("https://www.linkedin.com/in/furkanyesilyurt/")
                .code("link")
                .build();

        return Arrays.asList(url1, url2);
    }
}
