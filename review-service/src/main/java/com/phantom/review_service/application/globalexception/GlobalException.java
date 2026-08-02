package com.phantom.review_service.application.globalexception;

import com.phantom.review_service.application.classexception.DoctorReviewException;
import com.phantom.review_service.application.classexception.GlocalReviewException;
import com.phantom.review_service.application.classexception.HospitalReviewException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(DoctorReviewException.class)
    public ResponseEntity<String> handleDoctorReviewException(DoctorReviewException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(HospitalReviewException.class)
    public ResponseEntity<String> handleHospitalReviewException(HospitalReviewException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(GlocalReviewException.class)
    public ResponseEntity<String> handleGlocalReviewException(GlocalReviewException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }

}
