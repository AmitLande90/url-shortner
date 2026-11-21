package com.example.urlShortener.utils;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class UrlValidator {
    public boolean isValidUrl(String url) {
        if (url == null || url.isBlank()) {
            return false;
        }
        try {
            URI uri = new URI(url.trim());

            String schema = uri.getScheme();

            if (schema == null ||
                    ( !(schema.equalsIgnoreCase("http")) &&
                    !(schema.equalsIgnoreCase("https")))) {
                return false;
            }

            return uri.getHost() != null
                    && uri.getHost().contains(".");
            }
        catch (URISyntaxException e) {
            return false;
        }
    }
}
