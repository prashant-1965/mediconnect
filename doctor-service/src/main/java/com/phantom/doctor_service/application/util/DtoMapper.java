package com.phantom.doctor_service.application.util;

import com.phantom.doctor_service.application.entity.Doctor;
import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.dto.request.DoctorFacilityRegisterDto;
import com.phantom.dto.request.DoctorRegisterDto;
import com.phantom.util.UIDGenerator;

import java.util.List;

public class DtoMapper {
    public static Doctor doctorMapper(DoctorRegisterDto doctorRegisterDto){
        Doctor doctor = new Doctor();
        doctor.setDoctorId(UIDGenerator.uidGenerator());
        doctor.setDoctorYearsOfExperience(doctorRegisterDto.getDoctorYearsOfExperience());
        doctor.setDoctorRating(0.0);
        doctor.setDoctorDetailAddress(doctorRegisterDto.getDoctorDetailAddress());
        doctor.setHospitalId(doctorRegisterDto.getHospitalId());
        return doctor;
    }

    public static DoctorFacilityRegisterDto doctorFacilityRegisterDto(Long doctorId, List<Long> facilityIds){
        DoctorFacilityRegisterDto doctorFacilityRegisterDto = new DoctorFacilityRegisterDto();
        doctorFacilityRegisterDto.setDoctorId(doctorId);
        doctorFacilityRegisterDto.setFacilityIdList(facilityIds);
        return doctorFacilityRegisterDto;
    }

    public static AppUserRegisterDto doctorAppUserMapper(DoctorRegisterDto doctorRegisterDto){
        AppUserRegisterDto appUserRegisterDto = new AppUserRegisterDto();
        appUserRegisterDto.setUserName(doctorRegisterDto.getDoctorName());
        appUserRegisterDto.setUserAge(doctorRegisterDto.getDoctorAge());
        appUserRegisterDto.setUserGender(doctorRegisterDto.getDoctorGender());
        appUserRegisterDto.setUserMobile(doctorRegisterDto.getDoctorMobile());
        appUserRegisterDto.setUserEmail(doctorRegisterDto.getDoctorEmail());
        appUserRegisterDto.setUserState(doctorRegisterDto.getStateName());
        appUserRegisterDto.setUserCountry(doctorRegisterDto.getCountryName());
        appUserRegisterDto.setRole("DOCTOR");
//        appUserRegisterDto.setPassword(doctorRegisterDto.getPassword());
        return appUserRegisterDto;
    }
}
