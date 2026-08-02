package com.phantom.identity_service.application.service;

import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.enums.UserRole;
import com.phantom.enums.UserStatus;
import com.phantom.projection.IdentityDetailProjection;
import com.phantom.projection.IdentityStatusProjection;

import java.util.List;

public interface IAppUserService {
    String addAppUser(AppUserRegisterDto appUserRegisterDto);
    String changeAppUserPasswordRequest(String userEmail, String newPassword);
    List<IdentityStatusProjection> findPendingUsers(UserRole role, UserStatus status);
    boolean findUserByAppUserId(Long appUserId);
    String updateUserStatus(Long appUserId, String userStatus);
    List<IdentityDetailProjection> findUserDetailByAppUserIds(List<Long> appUserIdList);
    boolean checkUserStatusByAppUserId(Long appUserId, UserStatus userStatus);
}
