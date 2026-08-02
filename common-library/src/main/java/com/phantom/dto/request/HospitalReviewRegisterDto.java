package com.phantom.dto.request;

import lombok.Data;

@Data
public class HospitalReviewRegisterDto {
    private double rating;
    private String comments;
    private Long appUserId;
    private Long hospitalId;
}
