package com.phantom.admin_service.application.controller;

import com.phantom.admin_service.application.service.IAdminService;
import com.phantom.projection.BrokerStatusProjection;
import com.phantom.projection.DoctorStatusProjection;
import com.phantom.projection.HospitalStatusProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final IAdminService adminService;

    @GetMapping("/pending-hospitals")
    public ResponseEntity<List<HospitalStatusProjection>> findPendingHospitals(){
        return ResponseEntity.status(200).body(adminService.findPendingHospitals());
    }

    @GetMapping("/pending-doctors")
    public ResponseEntity<List<DoctorStatusProjection>> findPendingDoctors(){
        return ResponseEntity.status(200).body(adminService.findPendingDoctors());
    }

    @GetMapping("/pending-brokers")
    public ResponseEntity<List<BrokerStatusProjection>> findPendingBrokers(){
        return ResponseEntity.status(200).body(adminService.findPendingBrokers());
    }

    @PatchMapping("/update-doctor-status/{doctorId}/{status}")
    public ResponseEntity<String> updateDoctorStatus(@PathVariable Long doctorId, @PathVariable String status){
        return ResponseEntity.status(200).body(adminService.updateDoctorStatus(doctorId, status));
    }

    @PatchMapping("/update-hospital-status/{hospitalId}/{status}")
    public ResponseEntity<String> updateHospitalStatus(@PathVariable Long hospitalId, @PathVariable String status){
        return ResponseEntity.status(200).body(adminService.updateHospitalStatus(hospitalId, status));
    }

    @PatchMapping("/update-broker-status/{brokerId}/{status}")
    public ResponseEntity<String> updateBrokerStatus(@PathVariable Long brokerId, @PathVariable String status){
        return ResponseEntity.status(200).body(adminService.updateBrokerStatus(brokerId, status));
    }
}
