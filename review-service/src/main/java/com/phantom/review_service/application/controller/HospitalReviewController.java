package com.phantom.review_service.application.controller;

import com.phantom.dto.request.HospitalReviewRegisterDto;
import com.phantom.review_service.application.service.IHospitalReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospital-reviews")
@RequiredArgsConstructor
public class HospitalReviewController {

    private final IHospitalReviewService hospitalReviewService;

    @PostMapping("/register")
    public ResponseEntity<String> hospitalReviewRegister(@RequestBody HospitalReviewRegisterDto hospitalReviewRegisterDto){
        return ResponseEntity.status(200).body(hospitalReviewService.hospitalReviewRegister(hospitalReviewRegisterDto));
    }
}
