package com.phantom.broker_service.application.globalexception;

import com.phantom.broker_service.application.classexception.BrokerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalException {

    public ResponseEntity<String> handleException(BrokerException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }
}
