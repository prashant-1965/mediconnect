package com.phantom.doctor_service.application.globalexception;

import com.phantom.doctor_service.application.classexception.DoctorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalClassException {

    @ExceptionHandler(DoctorException.class)
    public ResponseEntity<String> validateDoctor(DoctorException d){
        return ResponseEntity.status(d.getHttpStatus()).body(d.getMessage());
    }
}
