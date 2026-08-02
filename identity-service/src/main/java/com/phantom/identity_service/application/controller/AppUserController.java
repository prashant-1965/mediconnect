package com.phantom.identity_service.application.controller;

import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.enums.UserRole;
import com.phantom.enums.UserStatus;
import com.phantom.identity_service.application.service.IAppUserService;
import com.phantom.projection.IdentityDetailProjection;
import com.phantom.projection.IdentityStatusProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appUser")
@RequiredArgsConstructor
public class AppUserController {

    private final IAppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<String> appUserSignUp(@RequestBody AppUserRegisterDto appUserRegisterDto){
        return ResponseEntity.status(200).body(appUserService.addAppUser(appUserRegisterDto));
    }

    @GetMapping("/findPendingUsers")
    public List<IdentityStatusProjection> findPendingUsers(@RequestParam UserRole role, @RequestParam UserStatus status){
        return appUserService.findPendingUsers(role,status);
    }

    @GetMapping("/findUserByAppUserId")
    public Boolean findUserByAppUserId(@RequestParam Long appUserId){
        return appUserService.findUserByAppUserId(appUserId);
    }

    @GetMapping("/findUserDetailByAppUserIds")
    public List<IdentityDetailProjection> findUserDetailByAppUserIds(@RequestParam List<Long> appUserIdList){
        return appUserService.findUserDetailByAppUserIds(appUserIdList);
    }

    @GetMapping("/checkUserStatusByAppUserId")
    public Boolean checkUserStatusByAppUserId(@RequestParam Long appUserId, @RequestParam UserStatus userStatus){
        return appUserService.checkUserStatusByAppUserId(appUserId, userStatus);
    }

    @PatchMapping("/changePasswordRequest")
    public ResponseEntity<String> changeUserPassword(@RequestParam String userEmail, @RequestParam String newPassword){
        return ResponseEntity.status(200).body(appUserService.changeAppUserPasswordRequest(userEmail, newPassword));
    }

    @PatchMapping("/updateUserStatus")
    public String updateUserStatus(@RequestParam Long appUserId, @RequestParam String userStatus){
        return appUserService.updateUserStatus(appUserId, userStatus);
    }
}
