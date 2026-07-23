package com.phantom.identity_service.application.util;

import com.phantom.dto.request.AppUserRegisterDto;
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
        appUser.setUserCountry(appUserRegisterDto.getUserCountry());
        appUser.setUserEmail(appUserRegisterDto.getUserEmail());
        appUser.setUserGender(appUserRegisterDto.getUserGender());
        appUser.setUserState(appUserRegisterDto.getUserState());
        appUser.setRole(role);
        appUser.setUserStatus(DtoMapper.appUserStatusMapper(role.getRoleName()));
        appUser.setUserLocalDateTime(LocalDateTime.now());
        Long appUserId = UIDGenerator.uidGenerator();
        appUser.setUserId(appUserId);
        return appUser;
    }

    private static String appUserStatusMapper(String roleName){
        if(roleName.equals("ADMIN") || roleName.equals("USER")) return "Active";
        return "Pending";
    }
}
