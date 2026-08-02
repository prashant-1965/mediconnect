package com.phantom.review_service.application.service;

import com.phantom.dto.request.GlobalReviewRegisterDto;
import com.phantom.projection.GlobalReviewProjection;

import java.util.List;

public interface IGlobalReviewService {
    String globalReviewRegister(GlobalReviewRegisterDto globalReviewRegisterDto);
    List<GlobalReviewProjection> getAllGlobalReviews();
}
