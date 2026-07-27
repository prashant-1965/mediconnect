package com.phantom.identity_service.application.service;

import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.enums.UserRole;
import com.phantom.enums.UserStatus;
import com.phantom.projection.IdentityStatusProjection;

import java.util.List;

public interface IAppUserService {
    String addAppUser(AppUserRegisterDto appUserRegisterDto);
    String changeAppUserPasswordRequest(String userEmail, String newPassword);
    List<IdentityStatusProjection> findPendingUsers(UserRole role, UserStatus status);
}
