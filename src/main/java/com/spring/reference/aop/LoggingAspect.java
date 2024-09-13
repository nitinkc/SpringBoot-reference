package com.spring.reference.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.spring.reference.service.UserServiceForAOP.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("AOP : Before method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.spring.reference.service.UserServiceForAOP.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("AOP : After method: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.spring.reference.service.UserServiceForAOP.updateUserExceptionally(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("AOP : Exception in method: {} with message: {}", joinPoint.getSignature().getName(), exception.getMessage());
    }
}
