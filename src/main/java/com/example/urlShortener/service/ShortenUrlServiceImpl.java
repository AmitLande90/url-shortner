package com.example.urlShortener.service;

import com.example.urlShortener.dto.ShortenUrlRequest;
import com.example.urlShortener.dto.ShortenUrlResponse;
import com.example.urlShortener.entity.UrlEntity;
import com.example.urlShortener.exceptions.ShortCodeNotFoundException;
import com.example.urlShortener.exceptions.InvalidUrlException;
import com.example.urlShortener.exceptions.ShortCodeExpiredException;
import com.example.urlShortener.repository.UrlRepository;
import com.example.urlShortener.utils.ShortCodeGenerator;
import com.example.urlShortener.utils.UrlValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShortenUrlServiceImpl implements ShortenUrlService {
    private final ShortCodeGenerator shortCodeGenerator;
    private final UrlValidator urlValidator;
    private final UrlRepository urlRepository;
    private static final String BASE_URL = "http://localhost:8080/";
    private static final Integer MAX_ATTEMPTS = 5;

    @Override
    @Transactional
    public ShortenUrlResponse shortUrl(ShortenUrlRequest request) {
        String originalUrl = request.getOriginalRequest();
        if (!urlValidator.isValidUrl(originalUrl)) {
            throw new IllegalArgumentException("Url is not valid");
        }
        String shortCode = generateUniqueShortCode();
        saveShortCode(shortCode, request.getOriginalRequest());
        return ShortenUrlResponse.builder()
                .originalUrl(request.getOriginalRequest())
                .shortCode(shortCode)
                .shortUrl(BASE_URL + shortCode)
                .build();

    }

    @Override
    public String getOriginalUrl(String shortCode) {
        UrlEntity urlEntity = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ShortCodeNotFoundException("Short URL not found"));

        if (urlEntity.getExpiresAt() != null &&
        !urlEntity.getExpiresAt().isAfter(Instant.now())) {
            throw new ShortCodeExpiredException("Short URL has expired");
        }

        return urlEntity.getOriginalUrl();
    }

    @Override
    public ResponseEntity<Void> redirect(String shortCode) {
        return null;
    }

    private String generateUniqueShortCode() {
        for (int i=0; i<MAX_ATTEMPTS; i++) {
            String shortCode = shortCodeGenerator.generateShortCode();
            if (!urlRepository.existsByShortCode(shortCode)) {
                return shortCode;
            }
        }
        throw new InvalidUrlException("Unable to generate unique short code");
    }

    private void saveShortCode(String shortCode, String originalUrl) {
        UrlEntity urlEntity = UrlEntity.builder()
                .originalUrl(originalUrl)
                .shortCode(shortCode)
                .build();

        urlRepository.save(urlEntity);
    }
}
