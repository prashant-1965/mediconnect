package com.phantom.hospital_service.application.service;

import com.phantom.dto.request.HospitalRegisterDto;

public interface IHospitalService {
    String hospitalRegistrationRequest(HospitalRegisterDto hospitalRegisterDto);
    boolean findHospitalByHospitalId(Long hospitalId);
}
