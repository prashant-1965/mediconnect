package com.phantom.facility_service.application.service;

import com.phantom.dto.request.FacilityRegisterDto;
import com.phantom.dto.response.FacilityListProjection;

import java.util.List;

public interface IFacilityService {
    List<Long> registerFacility(List<FacilityRegisterDto> facilityRegisterDtos);
    List<FacilityListProjection> findAllAvailableFacilities();
    List<Long> findAllFacilityIdByName(List<String> facilityNames);
//    List<String> findFacilityByDoctorEmail(String doctorEmail);
//    List<String> findFacilityByHospitalName(String hospitalName);
}
