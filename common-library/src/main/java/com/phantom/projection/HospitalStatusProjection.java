package com.phantom.projection;

import com.phantom.enums.UserStatus;
import lombok.Data;

@Data
public class HospitalStatusProjection {
    private String userName;
    private int userAge;
    private String userGender;
    private String userMobile;
    private String userEmail;
    private Long userCountry;
    private Long userState;
    private UserStatus userStatus;
    private Long hospitalId;
    private String hospitalType; // Gov or Private
    private String hospitalAddress;
    private int hospitalYearOfEstablishment;
    private int hospitalNumOfUsersServed;
    private double hospitalRating;
}
