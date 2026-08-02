package com.phantom.review_service.application.classexception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HospitalReviewException extends RuntimeException {
    private final HttpStatus httpStatus;
    public HospitalReviewException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
