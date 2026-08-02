package com.phantom.doctor_service.application.controller;

import com.phantom.doctor_service.application.service.IDoctorService;
import com.phantom.dto.request.DoctorRegisterDto;
import com.phantom.projection.DoctorStatusProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final IDoctorService doctorService;

    @PostMapping("/register")
    public ResponseEntity<String> registerDoctor(@RequestBody DoctorRegisterDto doctorRegisterDto){
        return ResponseEntity.status(200).body(doctorService.registerDoctor(doctorRegisterDto));
    }

    @GetMapping("/findPendingDoctors/{status}")
    public List<DoctorStatusProjection> findPendingDoctors(@PathVariable String status){
        return doctorService.findPendingDoctors(status);
    }

    @GetMapping("/findDoctorByDoctorId/{doctorId}")
    public Boolean findDoctorByDoctorId(@PathVariable Long doctorId){
        return doctorService.findDoctorByDoctorId(doctorId);
    }

    @PatchMapping("/updateDoctorStatus/{doctorId}/{status}")
    public String updateDoctorStatus(@PathVariable Long doctorId, @PathVariable String status){
        return doctorService.updateDoctorStatus(doctorId, status);
    }

    @PatchMapping("/updateDoctorRating/{doctorId}/{newRating}/{totalReview}")
    public Boolean updateDoctorRating(@PathVariable Long doctorId, @PathVariable double newRating, @PathVariable int totalReview){
        return doctorService.updateDoctorRating(doctorId, newRating, totalReview);
    }

}