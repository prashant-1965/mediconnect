package com.phantom.hospital_service.application.util;

import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.dto.request.FacilityRegisterDto;
import com.phantom.dto.request.HospitalFacilityRegisterDto;
import com.phantom.dto.request.HospitalRegisterDto;
import com.phantom.enums.UserRole;
import com.phantom.hospital_service.application.entity.Hospital;
import com.phantom.projection.HospitalStatusProjection;
import com.phantom.projection.IdentityStatusProjection;

import java.util.List;

public class DtoMapper {
    public static Hospital hospitalMapper(HospitalRegisterDto hospitalRegisterDto){
        Hospital hospital = new Hospital();
        hospital.setHospitalType(hospitalRegisterDto.getHospitalType());
        hospital.setHospitalAddress(hospital.getHospitalAddress());
        hospital.setHospitalYearOfEstablishment(hospitalRegisterDto.getHospitalYearOfEstablishment());
        hospital.setHospitalNumOfUsersServed(hospitalRegisterDto.getHospitalNumOfUsersServed());
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

    public static AppUserRegisterDto hospitalAppUserMapper(HospitalRegisterDto hospitalRegisterDto){
        AppUserRegisterDto appUserRegisterDto = new AppUserRegisterDto();
        appUserRegisterDto.setUserName(hospitalRegisterDto.getHospitalName());
        appUserRegisterDto.setUserAge(0);
        appUserRegisterDto.setUserGender("Not Applicable");
        appUserRegisterDto.setUserMobile(hospitalRegisterDto.getHospitalMobile());
        appUserRegisterDto.setUserEmail(hospitalRegisterDto.getHospitalEmail());
        appUserRegisterDto.setUserCountry(hospitalRegisterDto.getCountryName());
        appUserRegisterDto.setUserState(hospitalRegisterDto.getStateName());
        appUserRegisterDto.setRole(UserRole.HOSPITAL);
//        appUserRegisterDto.setUserPassword(hospitalRegisterDto.getHospitalPassword());
        return appUserRegisterDto;
    }

    public static HospitalStatusProjection HospitalIdentityMapper(Hospital hospital, IdentityStatusProjection identityStatusProjection){
        HospitalStatusProjection hospitalStatusProjection = new HospitalStatusProjection();
        hospitalStatusProjection.setUserName(identityStatusProjection.getUserName());
        hospitalStatusProjection.setUserAge(hospitalStatusProjection.getUserAge());
        hospitalStatusProjection.setUserGender(hospitalStatusProjection.getUserGender());
        hospitalStatusProjection.setUserMobile(hospitalStatusProjection.getUserMobile());
        hospitalStatusProjection.setUserEmail(hospitalStatusProjection.getUserEmail());
        hospitalStatusProjection.setUserCountry(hospitalStatusProjection.getUserCountry());
        hospitalStatusProjection.setUserState(hospitalStatusProjection.getUserState());
        hospitalStatusProjection.setUserStatus(hospitalStatusProjection.getUserStatus());
        hospitalStatusProjection.setHospitalId(hospital.getHospitalId());
        hospitalStatusProjection.setHospitalType(hospital.getHospitalType());
        hospitalStatusProjection.setHospitalAddress(hospital.getHospitalAddress());
        hospitalStatusProjection.setHospitalYearOfEstablishment(hospital.getHospitalYearOfEstablishment());
        hospitalStatusProjection.setHospitalNumOfUsersServed(hospital.getHospitalNumOfUsersServed());
        hospitalStatusProjection.setHospitalRating(hospital.getHospitalRating());

        return hospitalStatusProjection;
    }
}
