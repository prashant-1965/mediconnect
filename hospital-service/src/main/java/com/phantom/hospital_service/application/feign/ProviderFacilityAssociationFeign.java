package com.phantom.hospital_service.application.feign;

import com.phantom.dto.request.HospitalFacilityRegisterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "provider-facility-association-service")
public interface ProviderFacilityAssociationFeign {

    @PostMapping("/hospital-facility/register")
    String registerHospitalFacility(@RequestBody HospitalFacilityRegisterDto hospitalFacilityRegisterDto);
}
