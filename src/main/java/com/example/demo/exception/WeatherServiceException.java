package com.example.demo.exception;

// WeatherServiceException is a custom exception class that extends RuntimeException.
public class WeatherServiceException extends RuntimeException {

    // Constructor for creating a WeatherServiceException with a custom error message.
    public WeatherServiceException(String message) {
        super(message);
    }

    // Constructor for creating a WeatherServiceException with a custom error message and a nested Throwable cause.
    public WeatherServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

