package com.phantom.facility_service.application.classexception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FacilityException extends RuntimeException {
    private final HttpStatus httpStatus;
    public FacilityException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
