package com.phantom.doctor_service.application.classexception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DoctorException extends RuntimeException {
    final private HttpStatus httpStatus;
    public DoctorException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
