package com.phantom.doctor_service.application.service;

import com.phantom.dto.request.DoctorRegisterDto;

public interface IDoctorService {
    String registerDoctor(DoctorRegisterDto doctorRegisterDto);
}
