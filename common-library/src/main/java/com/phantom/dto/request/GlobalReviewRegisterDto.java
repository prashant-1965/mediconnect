package com.phantom.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class GlobalReviewRegisterDto {
    private Long appUserId;
    private double rating;
    private String comments;
}
