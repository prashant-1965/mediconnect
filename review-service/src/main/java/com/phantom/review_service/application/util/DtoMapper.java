package com.phantom.review_service.application.util;

import com.phantom.dto.request.DoctorReviewRegisterDto;
import com.phantom.dto.request.GlobalReviewRegisterDto;
import com.phantom.dto.request.HospitalReviewRegisterDto;
import com.phantom.projection.GlobalReviewProjection;
import com.phantom.projection.IdentityDetailProjection;
import com.phantom.review_service.application.entity.DoctorReview;
import com.phantom.review_service.application.entity.GlobalReview;
import com.phantom.review_service.application.entity.HospitalReview;
import com.phantom.util.UIDGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DtoMapper {
    public static DoctorReview doctorReviewMapper(DoctorReviewRegisterDto doctorReviewRegisterDto) {
        DoctorReview doctorReview = new DoctorReview();
        doctorReview.setAppUserId(doctorReviewRegisterDto.getAppUserId());
        doctorReview.setDoctorId(doctorReviewRegisterDto.getDoctorId());
        doctorReview.setComments(doctorReviewRegisterDto.getComments());
        doctorReview.setCreatedAt(LocalDateTime.now());
        doctorReview.setReviewId(UIDGenerator.uidGenerator());
        return doctorReview;
    }

    public static HospitalReview hospitalReviewMapper(HospitalReviewRegisterDto hospitalReviewRegisterDto) {
        HospitalReview hospitalReview = new HospitalReview();
        hospitalReview.setAppUserId(hospitalReviewRegisterDto.getAppUserId());
        hospitalReview.setHospitalId(hospitalReviewRegisterDto.getHospitalId());
        hospitalReview.setComments(hospitalReviewRegisterDto.getComments());
        hospitalReview.setCreatedAt(LocalDateTime.now());
        hospitalReview.setReviewId(UIDGenerator.uidGenerator());
        return hospitalReview;
    }

    public static GlobalReview globalReviewMapper(GlobalReviewRegisterDto globalReviewRegisterDto){
        GlobalReview globalReview = new GlobalReview();
        globalReview.setAppUserId(globalReviewRegisterDto.getAppUserId());
        globalReview.setRating(globalReviewRegisterDto.getRating());
        globalReview.setComments(globalReviewRegisterDto.getComments());
        globalReview.setCreatedAt(LocalDateTime.now());
        globalReview.setReviewId(UIDGenerator.uidGenerator());
        return globalReview;
    }

    public static List<GlobalReviewProjection> globalReviewMapper(List<GlobalReviewRegisterDto> globalReviewRegisterDtoS, Map<Long,String> identityDetailProjections){
        List<GlobalReviewProjection> globalReviewProjections = new ArrayList<>();
        for(GlobalReviewRegisterDto globalReviewRegisterDto:globalReviewRegisterDtoS){
            GlobalReviewProjection globalReviewProjection = new GlobalReviewProjection();
            globalReviewProjection.setUserName(identityDetailProjections.get(globalReviewRegisterDto.getAppUserId()));
            globalReviewProjection.setRating(globalReviewRegisterDto.getRating());
            globalReviewProjection.setComments(globalReviewRegisterDto.getComments());
            globalReviewProjections.add(globalReviewProjection);
        }
        return globalReviewProjections;
    }
}
