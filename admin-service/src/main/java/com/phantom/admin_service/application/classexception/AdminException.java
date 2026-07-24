package com.phantom.admin_service.application.classexception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AdminException extends RuntimeException {
    private final HttpStatus status;
    public AdminException(String message,HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;
    }
}
