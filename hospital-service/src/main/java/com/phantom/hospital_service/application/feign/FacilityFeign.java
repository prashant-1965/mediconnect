package com.phantom.hospital_service.application.feign;

import com.phantom.dto.request.FacilityRegisterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "facility-service")
public interface FacilityFeign {
    @PostMapping("/facility/register")
    List<Long> registerFacility(@RequestBody List<FacilityRegisterDto> facilityRegisterDtos);
}
