package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pro.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect//切面类aop类
public class LogAscept {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperateLogMapper operateLogMapper;



    @Around("@annotation(com.itheima.anno.Log)")//对于添加了Log注解的方法实行
    public Object record(ProceedingJoinPoint joinPoint) throws Throwable {
        //解析令牌，获得name，
        String jwt= request.getHeader("token");
        Claims claims=JwtUtils.parseJWT(jwt);//包含用户信息
        Integer id=(Integer) claims.get("id");//get id

        LocalDateTime localDateTime=LocalDateTime.now();//操作时间

        String classname = joinPoint.getTarget().getClass().getName();//获得方法的类名
        String methodname = joinPoint.getSignature().getName();//获得方法名
        Object[] args = joinPoint.getArgs();//获得参数
        String methodParams = Arrays.toString(args);


        long begin = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long end= System.currentTimeMillis();
        String returnValue = JSONObject.toJSONString(proceed);//获得返回值
        long time=end-begin;
        OperateLog operateLog=new OperateLog(null,id,localDateTime,classname,methodname,methodParams,returnValue,time);
        operateLogMapper.insert(operateLog);
        log.info("AOP操作日志",operateLog);
        return proceed;
    }
    
}
