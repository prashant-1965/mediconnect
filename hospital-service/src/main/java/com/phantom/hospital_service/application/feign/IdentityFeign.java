package com.phantom.hospital_service.application.feign;

import com.phantom.dto.request.AppUserRegisterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "identity-service")
public interface IdentityFeign {
    @PostMapping("/appUser/register")
    ResponseEntity<String> userSignUp(@RequestBody AppUserRegisterDto appUserRegisterDto);
}
