package com.phantom.doctor_service.application.controller;

import com.phantom.doctor_service.application.service.IDoctorService;
import com.phantom.dto.request.DoctorRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final IDoctorService doctorService;

    @PostMapping("/register")
    public ResponseEntity<String> registerDoctor(@RequestBody DoctorRegisterDto doctorRegisterDto){
        return ResponseEntity.status(200).body(doctorService.registerDoctor(doctorRegisterDto));
    }


}