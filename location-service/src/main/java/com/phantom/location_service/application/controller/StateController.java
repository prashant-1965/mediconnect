package com.phantom.location_service.application.controller;

import com.phantom.dto.request.StateRegisterDto;
import com.phantom.location_service.application.service.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/state")
public class StateController {
    @Autowired
    private IStateService IStateService;

    @PostMapping("/register")
    public ResponseEntity<String> registerDoctor(@RequestBody StateRegisterDto stateRegisterDto){
        return ResponseEntity.status(200).body(IStateService.addState(stateRegisterDto));
    }
}
