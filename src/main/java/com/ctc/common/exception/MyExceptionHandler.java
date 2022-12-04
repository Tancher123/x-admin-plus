package com.ctc.common.exception;

import com.ctc.common.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-07 18:08
 * @version:1.0
 **/
@ControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Object> myExceptionHandler (Exception e) {
        System.out.println ( "系统错误，错误信息：" + e.getMessage ( ) );
        if ( e.getMessage () == null ){
            return Result.fail ( "您未进行验证，请先验证" );
        }
        if ( e.getMessage ().contains ( "535" ) ){
            return Result.fail ( "发送邮箱验证码出现未知明错误，请联系管理员！" );
        }
        if ( e.getMessage ().contains ( "550" ) ){
            return Result.fail ( "您的邮箱有误，请确认无误后再操作" );
        }
        return Result.fail ( "未知明错误，如有问题联系管理员" );
    }
}
