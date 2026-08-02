package com.phantom.review_service.application.controller;

import com.phantom.dto.request.DoctorReviewRegisterDto;
import com.phantom.review_service.application.service.IDoctorReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor-reviews")
@RequiredArgsConstructor
public class DoctorReviewController {

    private final IDoctorReviewService doctorReviewService;

    @PostMapping("/register")
    public ResponseEntity<String> doctorReviewRegister(@RequestBody DoctorReviewRegisterDto doctorReviewRegisterDto){
        return ResponseEntity.status(200).body(doctorReviewService.doctorReviewRegister(doctorReviewRegisterDto));
    }
}
