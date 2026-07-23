package com.phantom.provider_facility_association_service.application.globalexception;

import com.phantom.provider_facility_association_service.application.classexception.HospitalFacilityException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(HospitalFacilityException.class)
    public ResponseEntity<String> handleHospitalFacilityException(HospitalFacilityException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }
}
