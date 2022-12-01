package com.ctc.common.config;

import com.ctc.common.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-06 18:54
 * @version:1.0
 **/
@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier(value = "loginInterceptor")
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors (InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor ( loginInterceptor );
        //拦截的请求
        interceptorRegistration.addPathPatterns ( "/**" );
        //放行的请求
        interceptorRegistration.excludePathPatterns (
                "/login",
                "/user/login",
                "/register",
                "/user/register",
                "/findPwd",
                "/user/findPwd",
                "/user/updatePwd/**",
                "/user/logout",
                "/captcha",
                "/layui/**",
                "/lib/**",
                "/webjars/**",
                "/api/**",
                "/css/**",
                "/js/**",
                "/images/**",
                "/error"
        );
    }
}
