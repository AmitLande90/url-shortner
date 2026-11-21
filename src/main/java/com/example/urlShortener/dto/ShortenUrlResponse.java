package com.example.urlShortener.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShortenUrlResponse {
    private String shortCode;
    private String shortUrl;
    private String originalUrl;
}
