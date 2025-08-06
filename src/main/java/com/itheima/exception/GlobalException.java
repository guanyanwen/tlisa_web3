package com.itheima.exception;

import com.itheima.pro.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//全局异常处理器
public class GlobalException {
    @ExceptionHandler(Exception.class)//捕获什么类型的异常
    public Result ex(Exception ex){
        ex.printStackTrace();//打印异常
        return Result.error("发生异常，联系管理员");
    }

}
