package com.hljit.examol.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CrossDomainInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 允许客户端携带跨域cookie，此时origin值不能为“*”，只能为指定单一域名。！！开发时不要使用localhost访问
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 允许指定域访问跨域资源
        //response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:9006, http://127.0.0.1:8080");
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8080");// *
        // 允许浏览器发送的请求消息头
        //response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        // 允许浏览器在预检请求成功之后发送的实际请求方法名
        //response.setHeader("Access-Control-Allow-Methods", "DEFAULT,POST,PATCH,PUT,OPTIONS,DELETE,HEAD");
        response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
        // 浏览器缓存预检请求结果时间,单位:秒
        response.setHeader("Access-Control-Max-Age", "86400");
        return true;
    }

}
