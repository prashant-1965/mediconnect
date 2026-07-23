package com.phantom.location_service.application.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LoggingAspect {

    @Around("execution(* com.phantom.location_service.application.controller.CountryController.*(..))")
    public Object logAroundForCountryController(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        log.info("Http Request intercepted for {} method of country controller has been initiated by user", methodName);
        try {
            Object response = joinPoint.proceed();
            log.info("Http Request intercepted for {} method of country controller has been completed successfully", methodName);
            return response;
        } catch (Exception e) {
            log.error("Http Request intercepted for {} method of country controller failed with exception: {}", methodName, e.getMessage());
            throw e;
        }
    }

    @Around("execution(* com.phantom.location_service.application.controller.StateController.*(..))")
    public Object logAroundForStateController(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        log.info("Http Request intercepted for {} method of state controller has been initiated by user", methodName);
        try {
            Object response = joinPoint.proceed();
            log.info("Http Request intercepted for {} method of state controller has been completed successfully", methodName);
            return response;
        } catch (Exception e) {
            log.error("Http Request intercepted for {} method of state controller failed with exception: {}", methodName, e.getMessage());
            throw e;
        }
    }

    @Around("execution(* com.phantom.location_service.application.service.CountryServiceImpl.*(..))")
    public Object logAroundForCountryService(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        log.info("Request for {} method of country service has been initiated by user", methodName);
        try {
            Object response = joinPoint.proceed();
            log.info("Request for {} method of country service has been completed successfully", methodName);
            return response;
        } catch (Exception e) {
            log.error("Request for {} method of country service failed with exception: {}", methodName, e.getMessage());
            throw e;
        }
    }

    @Around("execution(* com.phantom.location_service.application.service.StateServiceImpl.*(..))")
    public Object logAroundForStateService(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        log.info("Request for {} method of state service has been initiated by user", methodName);
        try {
            Object response = joinPoint.proceed();
            log.info("Request for {} method of state service has been completed successfully", methodName);
            return response;
        } catch (Exception e) {
            log.error("Request for {} method of state service failed with exception: {}", methodName, e.getMessage());
            throw e;
        }
    }
}
