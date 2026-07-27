package com.phantom.admin_service.application.feign;

import com.phantom.projection.DoctorStatusProjection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "doctor-service")
public interface DoctorFeign {

    @GetMapping("/doctor/findPendingDoctors/{status}")
    List<DoctorStatusProjection> findPendingDoctors(@PathVariable String status);
}
