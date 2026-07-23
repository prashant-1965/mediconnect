package com.phantom.identity_service.application.classexception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppUserException extends RuntimeException{
    final private HttpStatus httpStatus;
    public AppUserException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
