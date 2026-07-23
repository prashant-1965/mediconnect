package com.phantom.hospital_service.application.globalexception;

import com.phantom.hospital_service.application.classexception.HospitalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalClassException {

    @ExceptionHandler(HospitalException.class)
    public ResponseEntity<String> validateHospital(HospitalException h){
        return ResponseEntity.status(h.getHttpStatus()).body(h.getMessage());
    }
}
