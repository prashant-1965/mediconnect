package com.phantom.provider_facility_association_service.application.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LoggingAspect {

    @Around("execution(* com.phantom.provider_facility_association_service.application.controller.HospitalFacilityController.*(..))")
    public Object logAroundForHospitalFacilityController(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        log.info("Http Request intercepted for {} method of HospitalFacility controller has been initiated by user", methodName);
        try {
            Object response = joinPoint.proceed();
            log.info("Http Request intercepted for {} method of HospitalFacility controller has been completed successfully", methodName);
            return response;
        } catch (Exception e) {
            log.error("Http Request intercepted for {} method of HospitalFacility controller failed with exception: {}", methodName, e.getMessage());
            throw e;
        }
    }

    @Around("execution(* com.phantom.provider_facility_association_service.application.service.HospitalFacilityServiceImpl.*(..))")
    public Object logAroundForHospitalFacilityServiceImpl(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        log.info("Request for {} method of HospitalFacility service has been initiated by user", methodName);
        try {
            Object response = joinPoint.proceed();
            log.info("Request for {} method of HospitalFacility service has been completed successfully", methodName);
            return response;
        } catch (Exception e) {
            log.error("Request for {} method of HospitalFacility service failed with exception: {}", methodName, e.getMessage());
            throw e;
        }
    }
}

