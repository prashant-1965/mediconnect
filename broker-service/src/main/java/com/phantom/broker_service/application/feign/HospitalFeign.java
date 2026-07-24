package com.phantom.broker_service.application.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hospital-service")
public interface HospitalFeign {
    @GetMapping("/hospital/findByHospitalId/{hospitalId}")
    boolean findHospitalByHospitalId(@PathVariable Long hospitalId);
}
