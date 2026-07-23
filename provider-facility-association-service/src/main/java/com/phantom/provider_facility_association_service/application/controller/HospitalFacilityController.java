package com.phantom.provider_facility_association_service.application.controller;


import com.phantom.dto.request.HospitalFacilityRegisterDto;
import com.phantom.provider_facility_association_service.application.service.IHospitalFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/hospital-facility")
public class HospitalFacilityController {

    private final IHospitalFacilityService hospitalFacilityService;

    @PostMapping("/register")
    public String registerHospitalFacility(@RequestBody HospitalFacilityRegisterDto hospitalFacilityRegisterDto){
        return hospitalFacilityService.registerHospitalFacility(hospitalFacilityRegisterDto);
    }
}
