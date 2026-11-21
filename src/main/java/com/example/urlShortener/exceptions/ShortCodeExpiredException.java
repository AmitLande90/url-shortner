package com.example.urlShortener.exceptions;

import com.example.urlShortener.utils.ShortCodeGenerator;

public class ShortCodeExpiredException extends RuntimeException{
    public ShortCodeExpiredException(String message) {
        super(message);
    }
}
