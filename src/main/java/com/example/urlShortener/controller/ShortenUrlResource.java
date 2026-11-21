package com.example.urlShortener.controller;

import com.example.urlShortener.dto.ShortenUrlRequest;
import com.example.urlShortener.dto.ShortenUrlResponse;
import com.example.urlShortener.service.ShortenUrlService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/urls")
public class ShortenUrlResource {
    public static final Logger LOGGER = LoggerFactory.getLogger(ShortenUrlResource.class);
    private final ShortenUrlService shortenUrlService;

    @PostMapping
    public ResponseEntity<ShortenUrlResponse> postUrl(@RequestBody ShortenUrlRequest request) {
        LOGGER.info("Received request to shorten URL: {}", request.getOriginalRequest());
        ShortenUrlResponse shortenUrlResponse = shortenUrlService.shortUrl(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(shortenUrlResponse);
    }

    @GetMapping("/{shortCode}")
    public String getOriginalUrl(@PathVariable(name = "shortCode") String shortCode) {
        LOGGER.info("Received request to get original URL for short doe: {}", shortCode);
        return shortenUrlService.getOriginalUrl(shortCode);
    }
}
