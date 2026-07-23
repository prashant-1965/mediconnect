package com.phantom.doctor_service.application.util;

import com.phantom.doctor_service.application.entity.Doctor;
import com.phantom.dto.request.DoctorFacilityRegisterDto;
import com.phantom.dto.request.DoctorRegisterDto;
import com.phantom.util.UIDGenerator;

import java.util.List;

public class DtoMapper {
    public static Doctor doctorMapper(DoctorRegisterDto doctorRegisterDto){
        Doctor doctor = new Doctor();
        doctor.setDoctorId(UIDGenerator.uidGenerator());
        doctor.setDoctorName(doctorRegisterDto.getDoctorName());
        doctor.setDoctorAge(doctorRegisterDto.getDoctorAge());
        doctor.setDoctorGender(doctorRegisterDto.getDoctorGender());
        doctor.setDoctorYearsOfExperience(doctorRegisterDto.getDoctorYearsOfExperience());
        doctor.setDoctorRating(0.0);
        doctor.setDoctorEmail(doctorRegisterDto.getDoctorEmail());
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
}
