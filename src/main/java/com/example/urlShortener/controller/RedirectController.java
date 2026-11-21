package com.example.urlShortener.controller;

import com.example.urlShortener.service.ShortenUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class RedirectController {
    private final ShortenUrlService shortenUrlService;

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable(name = "shortCode") String shortCode) {
        String originalUrl = shortenUrlService.getOriginalUrl(shortCode);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}
