package com.spring.reference.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

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

    @Around("execution(* com.spring.reference.service.UserServiceForAOP.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object proceed = null;
        try {
            // Proceed with the original method execution
            proceed = joinPoint.proceed();
        } finally {
            long executionTime = System.currentTimeMillis() - startTime;
            log.info("AOP : Method {} executed in {} ms", joinPoint.getSignature(), executionTime);
        }

        return proceed;
    }
}