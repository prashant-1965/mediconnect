package com.phantom.hospital_service.application.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.phantom.hospital_service.application.service.HospitalServiceImpl.*(..))")
    public Object hospitalServiceLogging(ProceedingJoinPoint joinPoint) throws Throwable{
        String methodName = joinPoint.getSignature().getName();
        try{
            log.info("Request received for method: {} inside Hospital service",methodName);
            Object response = joinPoint.proceed();
            log.info("Request has been successfully completed for method: {} inside Hospital service",methodName);
            return response;
        }catch (Exception e) {
            log.error("Request has failed for method: {} inside Hospital service due to {}",methodName,e.getMessage());
            throw e;
        }
    }

    @Around("execution(* com.phantom.hospital_service.application.controller.HospitalController.*(..))")
    public Object hospitalControllerLogging(ProceedingJoinPoint joinPoint) throws Throwable{
        String methodName = joinPoint.getSignature().getName();
        try{
            log.info("Request received for method: {} inside Hospital controller",methodName);
            Object response = joinPoint.proceed();
            log.info("Request has been successfully completed for method: {} inside Hospital controller",methodName);
            return response;
        }catch (Exception e) {
            log.error("Request has failed for method: {} inside Hospital controller due to {}",methodName,e.getMessage());
            throw e;
        }
    }
}
