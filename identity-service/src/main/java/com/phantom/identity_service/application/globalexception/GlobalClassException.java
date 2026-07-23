package com.phantom.identity_service.application.globalexception;

import com.phantom.identity_service.application.classexception.AppUserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalClassException {

    @ExceptionHandler(AppUserException.class)
    public ResponseEntity<String> validateAppUser(AppUserException a){
        return ResponseEntity.status(a.getHttpStatus()).body(a.getMessage());
    }
}
