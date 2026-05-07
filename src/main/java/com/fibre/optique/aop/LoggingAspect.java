package com.fibre.optique.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || " +
              "within(@org.springframework.stereotype.Service *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all beans in application's main packages.
     */
    @Pointcut("within(com.fibre.optique.controllers..*) || " +
              "within(com.fibre.optique.services..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("applicationPackagePointcut() || springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();

        log.info("==> Entering method: {}.{}() with arguments: {}", 
                 className, methodName, Arrays.toString(args));

        try {
            Object result = joinPoint.proceed();
            log.info("<== Exited method: {}.{}() successfully", className, methodName);
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                      className, methodName);
            throw e;
        } catch (Exception e) {
            log.error("Exception in {}.{}() with cause = {}", 
                      className, methodName, e.getCause() != null ? e.getCause() : "NULL");
            log.error("Exception message: {}", e.getMessage());
            throw e;
        }
    }
}
