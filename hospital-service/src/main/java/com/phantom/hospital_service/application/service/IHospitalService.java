package com.phantom.hospital_service.application.service;

import com.phantom.dto.request.HospitalRegisterDto;
import com.phantom.projection.HospitalStatusProjection;

import java.util.List;

public interface IHospitalService {
    String hospitalRegistrationRequest(HospitalRegisterDto hospitalRegisterDto);
    boolean findHospitalByHospitalId(Long hospitalId);
    List<HospitalStatusProjection> findPendingHospitals(String status);
    boolean updateHospitalRating(Long hospitalId, Double newRating, int totalReviews);
    String updateHospitalStatus(Long hospitalId, String status);
}
