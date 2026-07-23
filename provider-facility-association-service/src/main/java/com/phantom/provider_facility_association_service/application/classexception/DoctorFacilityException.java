package com.phantom.provider_facility_association_service.application.classexception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DoctorFacilityException extends RuntimeException {
    private final HttpStatus httpStatus;
    public DoctorFacilityException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
