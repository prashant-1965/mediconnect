package com.phantom.projection;

import com.phantom.enums.UserStatus;
import lombok.Data;

@Data
public class IdentityStatusProjection {
    private Long appUserId;
    private String userName;
    private int userAge;
    private String userGender;
    private String userMobile;
    private String userEmail;
    private Long userCountry;
    private Long userState;
    private UserStatus userStatus;
}
