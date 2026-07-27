package com.phantom.projection;

import com.phantom.enums.UserStatus;
import lombok.Data;

@Data
public class DoctorStatusProjection {
    private String userName;
    private int userAge;
    private String userGender;
    private String userMobile;
    private String userEmail;
    private Long userCountry;
    private Long userState;
    private UserStatus userStatus;
    private Long doctorId;
    private Long hospitalId;
    private int doctorYearsOfExperience;
    private double doctorRating;
    private String doctorDetailAddress;
}
