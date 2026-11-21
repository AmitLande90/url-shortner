package com.example.urlShortener.service;

import com.example.urlShortener.dto.ShortenUrlRequest;
import com.example.urlShortener.dto.ShortenUrlResponse;
import org.springframework.http.ResponseEntity;

public interface ShortenUrlService {
    ShortenUrlResponse shortUrl(ShortenUrlRequest request) throws IllegalArgumentException;
    String getOriginalUrl(String shortCode);
    ResponseEntity<Void> redirect(String shortCode);
}
