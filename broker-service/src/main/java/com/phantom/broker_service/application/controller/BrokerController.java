package com.phantom.broker_service.application.controller;

import com.phantom.broker_service.application.service.IBrokerService;
import com.phantom.dto.request.BrokerRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/broker")
@RequiredArgsConstructor
public class BrokerController {

    private final IBrokerService brokerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerBroker(@RequestBody BrokerRegisterDto brokerRegisterDto){
        return ResponseEntity.status(200).body(brokerService.registerBroker(brokerRegisterDto));
    }

}
