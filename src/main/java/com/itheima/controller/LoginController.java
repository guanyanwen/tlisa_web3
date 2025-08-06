package com.itheima.controller;

import com.itheima.pro.Emp;
import com.itheima.pro.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController

public class LoginController {
    @Autowired
    private EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        Emp e=empService.login(emp);
        log.info("用户情况{}",e);
        //登陆成功，那么创建令牌，否则不创
        if(e!=null){
            Map<String,Object> claim=new HashMap<>();
            claim.put("id",e.getId());
            claim.put("name",e.getName());
            claim.put("password",e.getUsername());//将用户信息传入
            String jwt = JwtUtils.generateJwt(claim);//创造令牌
            return Result.success(jwt);//给前端令牌
        }
        else{
            return Result.error("账号密码错误");

    }}

}
