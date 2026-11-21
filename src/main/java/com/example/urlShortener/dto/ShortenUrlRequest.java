package com.example.urlShortener.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShortenUrlRequest {
    private String originalRequest;
}
