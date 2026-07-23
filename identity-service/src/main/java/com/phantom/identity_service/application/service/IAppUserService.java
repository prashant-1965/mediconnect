package com.phantom.identity_service.application.service;

import com.phantom.dto.request.AppUserRegisterDto;

import java.util.Optional;

public interface IAppUserService {
    String addAppUser(AppUserRegisterDto appUserRegisterDto);
    String changeAppUserPasswordRequest(String userEmail, String newPassword);
}
