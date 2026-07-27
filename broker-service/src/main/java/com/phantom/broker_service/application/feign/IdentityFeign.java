package com.phantom.broker_service.application.feign;

import com.phantom.dto.request.AppUserRegisterDto;
import com.phantom.enums.UserRole;
import com.phantom.enums.UserStatus;
import com.phantom.projection.IdentityStatusProjection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "identity-service")
public interface IdentityFeign {
    @PostMapping("/appUser/register")
    ResponseEntity<String> userSignUp(@RequestBody AppUserRegisterDto appUserRegisterDto);

    @GetMapping("/appUser/findPendingUsers")
    List<IdentityStatusProjection> findPendingUsers(@RequestParam UserRole role, @RequestParam UserStatus status);
}
