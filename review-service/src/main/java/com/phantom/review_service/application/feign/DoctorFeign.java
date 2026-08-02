package com.phantom.review_service.application.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doctor-service")
public interface DoctorFeign {
    @GetMapping("/doctor/findDoctorByDoctorId/{doctorId}")
    Boolean findDoctorByDoctorId(@PathVariable Long doctorId);

    @PatchMapping("/doctor/updateDoctorRating/{doctorId}")
    Boolean updateDoctorRating(@PathVariable Long doctorId, @PathVariable double newRating, @PathVariable int totalReview);
}
