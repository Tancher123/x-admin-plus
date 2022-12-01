package com.ctc.common.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-07 18:11
 * @version:1.0
 **/
@Controller
public class NotFoundController implements ErrorController {

    public String getErrorPath(){
        return "/error";
    }

    @RequestMapping("/error")
    public String error(){
        return "error/404";
    }
}
