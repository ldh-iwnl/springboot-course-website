package com.mayikt.edu.aop;

import com.mayikt.edu.base.BaseApiController;
import com.mayikt.edu.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogExceptionAop extends BaseApiController {
    /**
     * aop around exception before and after
     * we can use around and exception and handle exception
     */

    @Around("execution(* com.mayikt.edu.controller.*.*(..))")
    public Object currentLimit(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        try{
            return proceedingJoinPoint.proceed();
        }catch (Exception e){
            log.error("LogExceptionAop currentLimit error:{}", e);
            return setResultError();
        }
    }
}