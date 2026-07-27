package com.phantom.dto.request;

import com.phantom.enums.UserRole;
import lombok.Data;

@Data
public class AppUserRegisterDto {
    private String userName;
    private int userAge;
    private String userGender;
    private String userMobile;
    private String userEmail;
    private String userCountry;
    private String userState;
    private UserRole role;
//    private String password;
}