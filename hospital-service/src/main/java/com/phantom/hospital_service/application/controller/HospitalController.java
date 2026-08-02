package com.phantom.hospital_service.application.controller;

import com.phantom.dto.request.HospitalRegisterDto;
import com.phantom.hospital_service.application.service.IHospitalService;
import com.phantom.projection.HospitalStatusProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/findPendingHospitals/{status}")
    public List<HospitalStatusProjection> findPendingHospitals(@PathVariable String status){
        return hospitalService.findPendingHospitals(status);
    }

    @PatchMapping("/updateHospitalRating/{hospitalId}/{newRating}/{totalReview}")
    public Boolean updateHospitalRating(@PathVariable Long hospitalId, @PathVariable double newRating, @PathVariable int totalReview){
        return hospitalService.updateHospitalRating(hospitalId, newRating, totalReview);
    }

    @PatchMapping("/updateHospitalStatus/{hospitalId}/{status}")
    public String updateHospitalStatus(@PathVariable Long hospitalId, @PathVariable String status){
        return hospitalService.updateHospitalStatus(hospitalId, status);
    }
}
