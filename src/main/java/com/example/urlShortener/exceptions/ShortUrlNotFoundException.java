package com.example.urlShortener.exceptions;

public class ShortUrlNotFoundException extends RuntimeException{
    public ShortUrlNotFoundException(String message){
        super(message);
    }
}
