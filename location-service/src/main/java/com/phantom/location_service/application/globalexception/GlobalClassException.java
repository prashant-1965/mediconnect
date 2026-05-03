package com.phantom.location_service.application.globalexception;

import com.phantom.location_service.application.classexception.CountryException;
import com.phantom.location_service.application.classexception.StateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalClassException {

    @ExceptionHandler(CountryException.class)
    public ResponseEntity<String> validateCountry(CountryException e){
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @ExceptionHandler(StateException.class)
    public ResponseEntity<String> validateState(StateException e){
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }
}
