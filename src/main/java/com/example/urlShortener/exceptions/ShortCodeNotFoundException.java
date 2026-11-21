package com.example.urlShortener.exceptions;

public class ShortCodeNotFoundException extends RuntimeException{
    public ShortCodeNotFoundException(String message) {
        super(message);
    }
}
