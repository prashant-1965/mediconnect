package com.phantom.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class DoctorRegisterDto {
    private String doctorName;
    private int doctorAge;
    private String doctorGender;
    private int doctorYearsOfExperience;
    private String doctorEmail;
    private String doctorMobile;
    private String doctorDetailAddress;
    private String countryName;
    private String stateName;
    private Long hospitalId;
    private List<String> facilityNames;
//    private String password;
}
