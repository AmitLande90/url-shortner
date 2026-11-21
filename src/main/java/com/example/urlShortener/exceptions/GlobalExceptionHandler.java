package com.example.urlShortener.exceptions;

import com.example.urlShortener.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<ErrorResponse> handleException(InvalidUrlException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("Invalid_URL")
                .errorMessage(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ShortUrlNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ShortUrlNotFoundException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("SHORT_URL_NOT_FOUND")
                .errorMessage(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    public ResponseEntity<ErrorResponse> handleGenericResponse(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("INTERNAL_SERVER_ERROR")
                .errorMessage(e.getMessage())
                .build();
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
