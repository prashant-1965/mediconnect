package com.phantom.review_service.application.feign;

import com.phantom.projection.IdentityDetailProjection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "identity-service")
public interface IdentityFeign {
    @GetMapping("/appUser/findUserByAppUserId")
    Boolean findUserByAppUserId(@RequestParam Long appUserId);

    @GetMapping("/appUser/findUserDetailByAppUserIds")
    List<IdentityDetailProjection> findUserDetailByAppUserIds(@RequestParam List<Long> appUserIdList);
}
