package com.phantom.provider_facility_association_service.application.service;

import com.phantom.dto.request.HospitalFacilityRegisterDto;

public interface IHospitalFacilityService {

    String registerHospitalFacility(HospitalFacilityRegisterDto hospitalFacilityRegisterDto);
}
