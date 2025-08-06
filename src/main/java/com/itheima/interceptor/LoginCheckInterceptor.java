package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pro.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component//因为这个是spring的，所以可以给ioc容器
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//目标资源方法运行前运行，true执行，false不执行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
//        HttpServletRequest req=(HttpServletRequest) servletRequest;//强转请求，里面包含很多（路径，请求内容
//        HttpServletResponse res=(HttpServletResponse) servletResponse;
        String url=req.getRequestURI();
        log.info("url:"+url);
        //如果在登录页面，马上放行
        if(url.contains("login")){
            log.info("登陆页面，直接放行");
//            filterChain.doFilter(servletRequest,servletResponse);
            return true;//此处return true就可以放行，不用doFilter
        }
        String jwt= req.getHeader("token");//根据req，获取令牌
        //令牌为空
        if(StringUtils.hasLength(jwt)==false){
            log.info("token为空");
            Result err=Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(err);//将Resut转化为json
            res.getWriter().write(jsonString);//json传给响应体
            return false;//不放行，就false
        }
        //不为空，那就解析jwt
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {//解析失败,那就还在原来的界面，但是响应体里添加json数据
            log.info("令牌无效");
            Result err=Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(err);//将Resut转化为json
            res.getWriter().write(jsonString);//json传给响应体
            return false;
        }
        log.info("令牌没问题，放行");
        //回归正常，前往目标页面
//        filterChain.doFilter(servletRequest,servletResponse);
        return true;//改为return ture
    }

    @Override//目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override//视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
