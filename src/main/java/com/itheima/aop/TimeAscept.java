package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Slf4j
@Component
//@Aspect//aop类
public class TimeAscept {

    @Around("execution(* com.itheima.service.*.*(..))")//范围
    public Object recordtime(ProceedingJoinPoint joinPoint) throws Throwable {


        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();//执行原始方法。和它的返回值
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature()/*获实施的方法的签名*/+"耗时{}",begin-end);


        return result;
    }

}
