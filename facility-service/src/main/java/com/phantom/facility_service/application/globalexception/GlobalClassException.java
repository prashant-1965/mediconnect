package com.phantom.facility_service.application.globalexception;

import com.phantom.facility_service.application.classexception.FacilityException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalClassException {

    @ExceptionHandler(FacilityException.class)
    public ResponseEntity<String> validateFacility(FacilityException f){
        return ResponseEntity.status(f.getHttpStatus()).body(f.getMessage());
    }
}
