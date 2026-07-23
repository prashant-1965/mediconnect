package com.phantom.doctor_service.application.feign;

import com.phantom.dto.request.DoctorFacilityRegisterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "provider-facility-association-service")
public interface ProviderFacilityAssociationFeign {

    @PostMapping("/doctor-facility/register")
    String registerDoctorFacility(@RequestBody DoctorFacilityRegisterDto doctorFacilityRegisterDto);
}
