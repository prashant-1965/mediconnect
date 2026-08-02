package com.phantom.review_service.application.classexception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DoctorReviewException extends RuntimeException {
    private final HttpStatus httpStatus;
    public DoctorReviewException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
