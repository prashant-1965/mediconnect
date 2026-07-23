package com.phantom.location_service.application.controller;

import com.phantom.dto.request.StateRegisterDto;
import com.phantom.dto.response.StateListProjection;
import com.phantom.location_service.application.service.IStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/state")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class StateController {

    private final IStateService stateService;

    @PostMapping("/register")
    public ResponseEntity<String> registerState(@RequestBody StateRegisterDto stateRegisterDto){
        return ResponseEntity.status(200).body(stateService.registerState(stateRegisterDto));
    }

    @GetMapping("/findByName/{stateName}")
    public Long findStateByName(@PathVariable String stateName){
        return stateService.findStateByName(stateName).get().getStateId();
    }

    @GetMapping("/findByCountry/{countryName}")
    public ResponseEntity<List<StateListProjection>> getStateListByCountry(@PathVariable String countryName){
        return ResponseEntity.status(200).body(stateService.getStateList(countryName));
    }
}
