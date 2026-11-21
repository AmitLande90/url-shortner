package com.example.urlShortener.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class ShortCodeGenerator {
    private static final String BASE_62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 7;
    private static final SecureRandom random = new SecureRandom();

    public String generateShortCode() {
        StringBuilder stringBuilder = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(BASE_62.length());
            stringBuilder.append(BASE_62.charAt(randomIndex));
        }

        return stringBuilder.toString();
    }
}
