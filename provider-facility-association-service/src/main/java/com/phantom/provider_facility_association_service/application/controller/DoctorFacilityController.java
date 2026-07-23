package com.phantom.provider_facility_association_service.application.controller;


import com.phantom.dto.request.DoctorFacilityRegisterDto;
import com.phantom.provider_facility_association_service.application.service.IDoctorFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor-facility")
public class DoctorFacilityController {

    private final IDoctorFacilityService doctorFacilityService;

    @PostMapping("/register")
    public String registerDoctorFacility(@RequestBody DoctorFacilityRegisterDto doctorFacilityRegisterDto){
        return doctorFacilityService.registerDoctorFacility(doctorFacilityRegisterDto);
    }
}
