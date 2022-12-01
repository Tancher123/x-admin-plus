package com.ctc.common.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-06 18:48
 * @version:1.0
 **/
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle (HttpServletRequest request , HttpServletResponse response , Object handler) throws Exception {
        //获取session,判断用户是否登录
        Object loginUsername = request.getSession ( ).getAttribute ( "loginUsername" );
        if ( loginUsername == null ) {
            //未登录   重定向到登录页面
            log.debug ( "未登录请求" + request.getRequestURL ( ) );
            request.setAttribute ( "msg" , "没有权限，请先登录！" );
            response.sendRedirect ( request.getContextPath ( ) + "/login" );

            return false;
        }
        log.debug ( "放行请求" + request.getRequestURL ( ) );
        return true;
    }

}
