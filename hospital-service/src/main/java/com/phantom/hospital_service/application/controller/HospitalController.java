package com.phantom.hospital_service.application.controller;

import com.phantom.dto.request.HospitalRegisterDto;
import com.phantom.hospital_service.application.service.IHospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hospital")
public class HospitalController {

    private final IHospitalService hospitalService;

    @PostMapping("/register")
    public ResponseEntity<String> hospitalRegistrationRequest(@RequestBody HospitalRegisterDto hospitalRegisterDto){
        return ResponseEntity.status(200).body(hospitalService.hospitalRegistrationRequest(hospitalRegisterDto));
    }

    @GetMapping("/findByHospitalId/{hospitalId}")
    public boolean findHospitalByHospitalId(@PathVariable Long hospitalId){
        return hospitalService.findHospitalByHospitalId(hospitalId);
    }
}
