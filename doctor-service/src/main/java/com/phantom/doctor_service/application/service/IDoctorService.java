package com.phantom.doctor_service.application.service;

import com.phantom.dto.request.DoctorRegisterDto;
import com.phantom.projection.DoctorStatusProjection;

import java.util.List;

public interface IDoctorService {
    String registerDoctor(DoctorRegisterDto doctorRegisterDto);
    List<DoctorStatusProjection> findPendingDoctors(String status);
}
