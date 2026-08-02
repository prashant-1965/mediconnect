package com.phantom.dto.request;

import lombok.Data;

@Data
public class DoctorReviewRegisterDto {
    private double rating;
    private String comments;
    private Long appUserId;
    private Long doctorId;
}
