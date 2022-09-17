package com.fyesilyurt.urlshortener.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@PropertySource("classpath:settings.properties")
public class RandomStringGenerator {

    @Value(value = "${code.generator.codeLength}")
    private int codeLength;

    @Value(value = "${code.generator.leftLimit}")
    private int leftLimit;

    @Value(value = "${code.generator.rightLimit}")
    private int rightLimit;

    public String generateRandomString() {
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(codeLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
