package com.phantom.review_service.application.controller;

import com.phantom.dto.request.GlobalReviewRegisterDto;
import com.phantom.review_service.application.service.IGlobalReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/global-reviews")
@RequiredArgsConstructor
public class GlobalReviewController {
    private final IGlobalReviewService globalReviewService;

    @PostMapping("/register")
    public ResponseEntity<String> globalReviewRegister(@RequestBody GlobalReviewRegisterDto globalReviewRegisterDto){
        return ResponseEntity.status(200).body(globalReviewService.globalReviewRegister(globalReviewRegisterDto));
    }
}
