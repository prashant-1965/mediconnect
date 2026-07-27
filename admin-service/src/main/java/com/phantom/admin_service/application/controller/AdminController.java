package com.phantom.admin_service.application.controller;

import com.phantom.admin_service.application.service.IAdminService;
import com.phantom.projection.BrokerStatusProjection;
import com.phantom.projection.DoctorStatusProjection;
import com.phantom.projection.HospitalStatusProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
