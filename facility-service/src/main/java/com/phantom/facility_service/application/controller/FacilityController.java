package com.phantom.facility_service.application.controller;


import com.phantom.dto.request.FacilityRegisterDto;
import com.phantom.dto.response.FacilityListProjection;
import com.phantom.facility_service.application.service.IFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facility")
@RequiredArgsConstructor
public class FacilityController {

    private final IFacilityService facilityService;

    @PostMapping("/register")
    public List<Long> registerFacility(@RequestBody List<FacilityRegisterDto> facilityRegisterDtos){
        return facilityService.registerFacility(facilityRegisterDtos);
    }

    @GetMapping("/facilityIdByName")
    public List<Long> findAllFacilityIdByName(@RequestBody List<String> facilityNames){
        return facilityService.findAllFacilityIdByName(facilityNames);
    }

    @GetMapping("/allFacilities")
    public ResponseEntity<List<FacilityListProjection>> findAvailableFacilities() {
        return ResponseEntity.status(200).body(facilityService.findAllAvailableFacilities());
    }

//    @GetMapping("/facilityByDoctorEmail")
//    public ResponseEntity<List<String>> findFacilityByDoctorEmail(@RequestParam String doctorEmail){
//        return ResponseEntity.status(200).body(facilityService.findFacilityByDoctorEmail(doctorEmail));
//    }
//    @GetMapping("/allFacilityByHospitalName")
//    public ResponseEntity<List<String>> findFacilityByHospitalName(@RequestParam String hospitalName){
//        return ResponseEntity.status(200).body(facilityService.findFacilityByHospitalName(hospitalName));
//    }
}
