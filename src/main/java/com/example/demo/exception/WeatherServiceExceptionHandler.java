package com.example.demo.exception;

import com.example.demo.models.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


// This class is used to handle exceptions globally for your Spring Boot application.
@ControllerAdvice
public class WeatherServiceExceptionHandler {

    // This method handles exceptions of type ResourceNotFoundException.
    @ExceptionHandler(ResourceNotFoundException.class)
    // It specifies the HTTP response status code to be returned when this exception occurs (404 - NOT FOUND).
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    // Indicates that the return value of this method should be serialized into the response body.
    public @ResponseBody ExceptionResponse handleResourceNotFound(ResourceNotFoundException exception) {

        // Create an ExceptionResponse object to hold the error message.
        ExceptionResponse error = new ExceptionResponse();

        // Set the error message of the ExceptionResponse to the message from the exception.
        error.setErrorMessage(exception.getMessage());

        // Return the ExceptionResponse, which will be serialized into the response body.
        return error;
    }

    @ExceptionHandler(Exception.class)
    // It specifies the HTTP response status code to be returned for generic exceptions (500 - INTERNAL SERVER ERROR).
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionResponse handleException(Exception exception) {

        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());

        return error;
    }
}
