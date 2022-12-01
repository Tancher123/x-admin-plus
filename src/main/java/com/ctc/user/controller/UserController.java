package com.ctc.user.controller;

import com.ctc.common.vo.Result;
import com.ctc.user.pojo.User;
import com.ctc.user.service.impl.UserServiceImpl;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: x-admin-plus
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-29 22:26
 * @version:1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    /**
     * @param user:
     * @param captcha:
     * @param request:
     * @param response:
     * @param session:
     * @param rememberMe:
     * @return Result<Object>
     * @author 陈天赐
     * @description 登录
     * @date 2022/11/30 11:46
     */
    @PostMapping("/login")
    public Result<Object> login (User user ,
                                 @RequestParam("captcha") String captcha ,
                                 HttpServletRequest request ,
                                 HttpServletResponse response ,
                                 HttpSession session ,
                                 @RequestParam("rememberMe") Boolean rememberMe) {
        //验证码判断
        if ( !CaptchaUtil.ver ( captcha , request ) ) {
            CaptchaUtil.clear ( request );  // 清除session中的验证码
            return Result.fail ( "验证码错误" );
        }
        User login = userService.login ( user );
        if ( login != null ) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder ( );
            //参数一是请求密码，参数二是数据库中的值
            boolean matches = passwordEncoder.matches ( user.getPassword ( ) , login.getPassword ( ) );
            if ( matches ) {
                //登录成功
                //记住账号
                Cookie c_password = new Cookie ( "password" , user.getPassword ( ) );
                Cookie c_username = new Cookie ( "username" , user.getUsername ( ) );
                session.setAttribute ( "username" , user.getUsername ( ) );
                c_username.setMaxAge ( 60 * 60 * 24 * 30 );
                response.addCookie ( c_username );
                //判断是否选择记住密码
                if ( rememberMe == true ) {
                    //勾选记住密码了
                    //设置存活时间
                    session.setAttribute ( "password" , user.getPassword ( ) );
                    session.setAttribute ( "rememberMe" , true );
                    c_password.setMaxAge ( 60 * 60 * 24 * 30 );
                    //发送
                    response.addCookie ( c_password );
                } else {
                    c_password.setMaxAge ( 0 );
                }
                session.setAttribute ( "loginUsername" , login.getUsername ( ) );
                session.setAttribute ( "loginChName" , login.getChName ( ) );
                session.setAttribute ( "userId" , login.getId ( ) );
                return Result.success ( "登录成功" , login.getUsername ( ) );
            }
        }
        //登录失败
        return Result.fail ( "用户名或密码错误" );
    }

    @PostMapping("/register")
    public Result<Object> register (User user ,
                                    @RequestParam("captcha") String captcha ,
                                    HttpServletRequest request) {
        //验证码判断
        if ( !CaptchaUtil.ver ( captcha , request ) ) {
            CaptchaUtil.clear ( request );  // 清除session中的验证码
            return Result.fail ( "验证码错误" );
        }
        if ( user.getUsername ( ).equals ( "chentianciSB" ) ||
                user.getUsername ( ).equals ( "chentiancisb" ) ||
                user.getUsername ( ).equals ( "chentianciissb" ) ||
                user.getUsername ( ).equals ( "chentianciisSB" ) ||
                user.getUsername ( ).equals ( "ctcsb123" ) ||
                user.getUsername ( ).equals ( "ctcSB123" ) ||
                user.getUsername ( ).equals ( "ctcisSB123" )
        ) {
            return Result.fail ( "不能出现敏感词汇！" );
        }

        if ( user.getUsername ( ) != null && user.getPassword ( ) != null ) {
            //将密码加密
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder ( );
            String addPwd = passwordEncoder.encode ( user.getPassword ( ) );
            String chName = "普通用户";
            User userByUsername = userService.login ( user );
            if ( userByUsername != null ) {
                //用户名已经存在
                return Result.fail ( "用户名已存在" );
            }
            user.setPassword ( addPwd );
            user.setChName ( chName );
            int i = userService.register ( user );
            if ( i > 0 ) {
                //注册成功
                return Result.success ( "注册成功" );
            }
        }
        //注册失败
        return Result.fail ( "注册失败" );
    }

    //修改基本信息
    @PutMapping("/account")
    public Result<Object> updateAccount (User user) {
        Integer age = user.getAge ( );
        if ( age > 120 || age < 0 ) {
            return Result.fail ( "您输入的年龄不合法，请确认后再输入！" );
        }
        int i = userService.updateUser ( user );
        if ( i > 0 ) {
            return Result.success ( "您的基本信息修改成功" );
        }
        return Result.fail ( "您的基本信息修改失败" );
    }

    //删除用户信息
    @DeleteMapping("{id}")
    public Result<Object> deleteUserAndAccount (@PathVariable("id") int id) {
        int i = userService.deleteUserById ( id );
        if ( i > 0 ) {
            //注销成功
            return Result.success ( "注销成功!" );
        }
        return Result.fail ( "注销失败请联系管理员" );
    }

    //修改密码
    @PutMapping("")
    public Result<Object> updatePassword (@RequestParam("id") int id ,
                                          @RequestParam("old_password") String old_password ,
                                          @RequestParam("password") String password) {
        User userById = userService.selectUserById ( id );
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder ( );
        //参数一是请求密码，参数二是数据库中的值
        boolean matches = passwordEncoder.matches ( old_password , userById.getPassword ( ) );
        if ( !matches ) {
            return Result.fail ( "旧密码错误" );
        }
        boolean newAndOld = passwordEncoder.matches ( password , userById.getPassword ( ) );
        if ( newAndOld ) {
            return Result.fail ( "新密码不能为近期用过的密码" );
        }

        String encode = passwordEncoder.encode ( password );
        User user = new User ( );
        user.setPassword ( encode );
        user.setId ( id );

        int i = userService.updateUser ( user );
        if ( i > 0 ) {
            return Result.success ( "密码修改成功重新登录" );
        }
        return Result.fail ( "密码修改失败" );
    }

    //找回密码前的信息验证
    @PostMapping("/findPwd")
    public Result<Object> findPwd (@RequestParam("username") String username ,
                                   @RequestParam("name") String name ,
                                   @RequestParam("card") String card) {
        User user = userService.selectUserByUsername ( username );
        if ( user == null ) {
            return Result.fail ( "账号不存在" );
        }
        if ( user.getCard ( ) == null ) {
            return Result.fail ( "您的账号未绑定身份证，找回失败，请联系管理员" );
        }
        if ( !user.getName ( ).equals ( name ) ) {
            return Result.fail ( "姓名匹配错误，验证失败！" );
        }
        if ( !user.getCard ( ).equals ( card ) ) {
            return Result.fail ( "身份证匹配错误，验证失败！" );
        }
        if ( user != null && user.getCard ( ).equals ( card ) && user.getName ( ).equals ( name ) ) {
            return Result.success ( "验证成功" , user.getId ( ) );
        } else
            return Result.fail ( "未知明错误，验证失败！" );
    }

    //密码找回
    @PutMapping("updatePwd/{id}")
    public Result<Object> updateUserPwd (@PathVariable("id") Integer id , @RequestParam("password") String password) {
        //将密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder ( );
        String updatePwd = passwordEncoder.encode ( password );
        User user = new User ( );
        user.setPassword ( updatePwd );
        user.setId ( id );
        int i = userService.updateUser ( user );
        if ( i > 0 ) {
            return Result.success ( );
        }
        return Result.fail ( "密码找回失败，请联系管理员！" );
    }

}
