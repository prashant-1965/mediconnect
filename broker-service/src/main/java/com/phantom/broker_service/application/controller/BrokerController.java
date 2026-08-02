package com.phantom.broker_service.application.controller;

import com.phantom.broker_service.application.service.IBrokerService;
import com.phantom.dto.request.BrokerRegisterDto;
import com.phantom.projection.BrokerStatusProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/broker")
@RequiredArgsConstructor
public class BrokerController {

    private final IBrokerService brokerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerBroker(@RequestBody BrokerRegisterDto brokerRegisterDto){
        return ResponseEntity.status(200).body(brokerService.registerBroker(brokerRegisterDto));
    }

    @GetMapping("/findPendingBrokers/{status}")
    public List<BrokerStatusProjection> findPendingBrokers(@PathVariable String status){
        return brokerService.findPendingBrokers(status);
    }

    @PatchMapping("/updateBrokerStatus/{brokerId}/{status}")
    public String updateBrokerStatus(@PathVariable Long brokerId, @PathVariable String status){
        return brokerService.updateBrokerStatus(brokerId, status);
    }
}
