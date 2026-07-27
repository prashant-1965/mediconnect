package com.phantom.admin_service.application.feign;

import com.phantom.projection.HospitalStatusProjection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "hospital-service")
public interface HospitalFeign {

    @GetMapping("/hospital/findPendingHospitals/{status}")
    List<HospitalStatusProjection> findPendingHospitals(@PathVariable String status);
}

