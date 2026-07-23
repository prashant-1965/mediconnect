package com.phantom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IndividualHospitalDetailProjection {
    private String hospitalName;
    private String hospitalType;
    private int hospitalYearOfEstablishment;
    private int hospitalNumOfUsersServed;
    private double hospitalRating;
    private String hospitalContact;
    private String hospitalAddress;
}
