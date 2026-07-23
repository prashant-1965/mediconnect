package com.phantom.doctor_service.application.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("facility-service")
public interface FacilityFeign {
    @GetMapping("/facility/facilityIdByName")
    List<Long> findAllFacilityIdByName(@RequestBody List<String> facilityNames);
}
