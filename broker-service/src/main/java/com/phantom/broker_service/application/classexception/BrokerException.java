package com.phantom.broker_service.application.classexception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BrokerException extends RuntimeException{
    private final HttpStatus httpStatus;
    public BrokerException(String message,HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
