package com.phantom.identity_service.application.util;

import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.enums.UserRole;
import com.phantom.enums.UserStatus;
import com.phantom.identity_service.application.entity.AppUser;
import com.phantom.identity_service.application.entity.Role;
import com.phantom.util.UIDGenerator;

import java.time.LocalDateTime;

public class DtoMapper {
    public static AppUser appUserMapper(AppUserRegisterDto appUserRegisterDto, Role role){
        AppUser appUser = new AppUser();
        appUser.setUserName(appUserRegisterDto.getUserName());
        appUser.setUserMobile(appUserRegisterDto.getUserMobile());
        appUser.setUserAge(appUserRegisterDto.getUserAge());
        appUser.setUserEmail(appUserRegisterDto.getUserEmail());
        appUser.setUserGender(appUserRegisterDto.getUserGender());
        appUser.setRole(role);
        appUser.setUserStatus(DtoMapper.appUserStatusMapper(role.getRoleName()));
        appUser.setUserLocalDateTime(LocalDateTime.now());
        Long appUserId = UIDGenerator.uidGenerator();
        appUser.setAppUserId(appUserId);
        return appUser;
    }

    private static UserStatus appUserStatusMapper(UserRole roleName){
        if(roleName == UserRole.ADMIN || roleName == UserRole.PATIENT) return UserStatus.ACTIVE;
        return UserStatus.PENDING;
    }
}
