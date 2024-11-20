package com.movie.crud_movie.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.movie.crud_movie.service.impl.MovieServiceImpl.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Executing: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.movie.crud_movie.service.impl.MovieServiceImpl.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        System.out.println("Finished: " + joinPoint.getSignature().getName() + " with result: " + result);
    }

    @AfterThrowing(value = "execution(* com.movie.crud_movie.service.impl.MovieServiceImpl.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        System.out.println("Exception in: " + joinPoint.getSignature().getName() + " with message: " + exception.getMessage());
    }
}
