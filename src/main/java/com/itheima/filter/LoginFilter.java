package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.google.gson.JsonObject;
import com.itheima.pro.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;//强转请求，里面包含很多（路径，请求内容
        HttpServletResponse res=(HttpServletResponse) servletResponse;
        String url=req.getRequestURI();
        log.info("url:"+url);
        //如果在登录页面，马上放行
        if(url.contains("login")){
            log.info("登陆页面，直接放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        String jwt= req.getHeader("token");//根据req，获取令牌
        //令牌为空
        if(StringUtils.hasLength(jwt)==false){
            log.info("token为空");
            Result err=Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(err);//将Resut转化为json
            res.getWriter().write(jsonString);//json传给响应体
            return;
        }
        //不为空，那就解析jwt
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {//解析失败,那就还在原来的界面，但是响应体里添加json数据
            log.info("令牌无效");
            Result err=Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(err);//将Resut转化为json
            res.getWriter().write(jsonString);//json传给响应体
            return;
        }
        log.info("令牌没问题，放行");
        //回归正常，前往目标页面
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
