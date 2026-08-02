package com.phantom.review_service.application.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hospital-service")
public interface HospitalFeign {
    @GetMapping("/hospital/findHospitalByHospitalId/{hospitalId}")
    Boolean findHospitalByHospitalId(@PathVariable Long hospitalId);

    @PatchMapping("/hospital/updateHospitalRating/{hospitalId}")
    Boolean updateHospitalRating(@PathVariable Long hospitalId, @PathVariable double newRating, @PathVariable int totalReview);
}
