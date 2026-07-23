package com.phantom.facility_service.application.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.phantom.facility_service.application.controller.*.*(..))")
    public Object facilityControllerLogging(ProceedingJoinPoint joinPoint) throws Throwable{
        String methodName = joinPoint.getSignature().getName();
        log.info("Http Request intercepted for {} method of facility controller has been initiated by user", methodName);
        try{
            Object response = joinPoint.proceed();
            log.info("Http Request intercepted for {} method of facility controller has been completed successfully", methodName);
            return response;
        }catch (Exception e){
            log.error("Http Request intercepted for {} method of facility controller failed with exception: {}", methodName, e.getMessage());
            throw e;
        }
    }

    @Around("execution(* com.phantom.facility_service.application.service.FacilityServiceImpl.*(..))")
    public Object facilityServiceLogging(ProceedingJoinPoint joinPoint) throws Throwable{
        String methodName = joinPoint.getSignature().getName();
        log.info("Request for {} method of facility service has been initiated by user", methodName);
        try{
            Object response = joinPoint.proceed();
            log.info("Request for {} method of facility service has been completed successfully", methodName);
            return response;
        }catch (Exception e){
            log.error("Request for {} method of facility service failed with exception: {}", methodName, e.getMessage());
            throw e;
        }
    }
}
