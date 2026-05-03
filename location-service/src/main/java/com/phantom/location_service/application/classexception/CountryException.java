package com.phantom.location_service.application.classexception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CountryException extends RuntimeException {
    final private HttpStatus httpStatus;
    public CountryException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}