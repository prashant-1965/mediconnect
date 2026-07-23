package com.phantom.identity_service.application.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ServiceLoggingAspect {
    @Around("execution(* com.phantom.identity_service.application.service.AppUserServiceImpl.*(..))")
    public Object appUserLogging(ProceedingJoinPoint joinPoint) throws Throwable{
        String methodName = joinPoint.getSignature().getName();
        log.info("Request for {} method of appUser service has been initiated by user", methodName);
        try{
            Object response = joinPoint.proceed();
            log.info("Request for {} method of appUser service has been completed successfully", methodName);
            return response;
        }catch (Exception e){
            log.error("Request for {} method of appUser service failed with exception: {}", methodName, e.getMessage());
            throw e;
        }
    }
}
