package com.phantom.provider_facility_association_service.application.service;

import com.phantom.dto.request.DoctorFacilityRegisterDto;

public interface IDoctorFacilityService {

    String registerDoctorFacility(DoctorFacilityRegisterDto doctorFacilityRegisterDto);
}
