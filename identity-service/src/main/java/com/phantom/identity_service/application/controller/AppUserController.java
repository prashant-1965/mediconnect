package com.phantom.identity_service.application.controller;

import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.identity_service.application.service.IAppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appUser")
@RequiredArgsConstructor
public class AppUserController {

    private final IAppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<String> clientSignUp(@RequestBody AppUserRegisterDto appUserRegisterDto){
        return ResponseEntity.status(200).body(appUserService.addAppUser(appUserRegisterDto));
    }

    @PutMapping("/changePasswordRequest")
    public ResponseEntity<String> changeUserPassword(@RequestParam String userEmail, @RequestParam String newPassword){
        return ResponseEntity.status(200).body(appUserService.changeAppUserPasswordRequest(userEmail, newPassword));
    }
}
