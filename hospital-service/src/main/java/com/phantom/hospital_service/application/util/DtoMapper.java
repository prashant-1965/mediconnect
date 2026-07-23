package com.phantom.hospital_service.application.util;

import com.phantom.dto.request.FacilityRegisterDto;
import com.phantom.dto.request.HospitalFacilityRegisterDto;
import com.phantom.dto.request.HospitalRegisterDto;
import com.phantom.hospital_service.application.entity.Hospital;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class DtoMapper {
    public static Hospital hospitalMapper(HospitalRegisterDto hospitalRegisterDto){
        Hospital hospital = new Hospital();
        hospital.setHospitalName(hospitalRegisterDto.getHospitalName());
        hospital.setHospitalEmail(hospitalRegisterDto.getHospitalEmail());
        hospital.setHospitalType(hospitalRegisterDto.getHospitalType());
        hospital.setHospitalYearOfEstablishment(hospitalRegisterDto.getHospitalYearOfEstablishment());
        hospital.setHospitalNumOfUsersServed(hospitalRegisterDto.getHospitalNumOfUsersServed());
        hospital.setHospitalMobile(hospitalRegisterDto.getHospitalMobile());
        hospital.setHospitalAddress(hospitalRegisterDto.getHospitalAddress());
        hospital.setHospitalStatus("PENDING");
        hospital.setHospitalRegistrationDateTime(LocalDateTime.now());
        return hospital;
    }

    public static FacilityRegisterDto hospitalFacilityMapper(String facilityName, String facilityDescription){
        FacilityRegisterDto facilityRegisterDto = new FacilityRegisterDto();
        facilityRegisterDto.setFacilityName(facilityName);
        facilityRegisterDto.setFacilityDescription(facilityDescription);
        return facilityRegisterDto;
    }

    public static HospitalFacilityRegisterDto hospitalFacilityMapper(Long hospitalId, List<Long> facilityIdList){
        HospitalFacilityRegisterDto hospitalFacilityRegisterDto = new HospitalFacilityRegisterDto();
        hospitalFacilityRegisterDto.setHospitalId(hospitalId);
        hospitalFacilityRegisterDto.setFacilityIdList(facilityIdList);
        return hospitalFacilityRegisterDto;
    }
}
