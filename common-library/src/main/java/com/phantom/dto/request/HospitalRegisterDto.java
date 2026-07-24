package com.phantom.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class HospitalRegisterDto {
    private String hospitalName;
    private String hospitalEmail;
    private String hospitalType;
    private int hospitalYearOfEstablishment;
    private int hospitalNumOfUsersServed;
    private String hospitalMobile;
    private String hospitalAddress;
    private String countryName;
    private String stateName;
    private Map<String,String> facilitiesWithDescription;
//    private String hospitalPassword;
}
