package com.ctc.common.controller;


import com.ctc.common.vo.Result;
import com.ctc.emp.pojo.Dept;
import com.ctc.emp.pojo.Emp;
import com.ctc.emp.service.impl.EmpServiceImpl;
import com.ctc.user.pojo.User;
import com.ctc.user.service.impl.UserServiceImpl;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-06 14:15
 * @version:1.0
 **/
@Controller
public class CommonController {
    @Autowired
    private EmpServiceImpl empService;
    @Autowired
    private UserServiceImpl userService;

    //跳转到登录页面
    @GetMapping("/login")
    public String login ( ) {
        return "login";
    }

    //登录跳转到index页面
    @GetMapping({"/" , "/index"})
    public String index ( ) {
        return "index";
    }

    //登录跳转到index页面
    @GetMapping("/welcome")
    public String welcome ( ) {
        return "welcome";
    }

    //登录跳转到details页面
    @GetMapping("/details")
    public String details ( ) {
        return "details";
    }

    //跳转到注册页面
    @GetMapping("/register")
    public String toRegister ( ) {
        return "register";
    }

    //跳转到密码修改页面
    @GetMapping("/user")
    public String toUpdatePassword ( ) {
        return "user/user-password";
    }

    //跳转到找回密码页面
    @GetMapping("/findPwd")
    public String toFindPwd ( ) {
        return "findPwd";
    }

    //跳转到员工分页列表
    @GetMapping("/emp")
    public String toEmpListUi ( ) {
        return "emp/empList";
    }

    //跳转到员工分页列表
    @GetMapping("/emp/deletedUi")
    public String toEmpDeletedUi ( ) {
        return "emp/empDeleted";
    }
    //跳转到修改邮箱页面
    @GetMapping("/email/{email}")
    public String toUpdateEmail (@PathVariable("email") String email,Model model) {
        model.addAttribute ( "email",email );
        return "user/userEmailUpdate";
    }

    //跳转到新增页面
    @GetMapping("emp/add/ui")
    public String addUi (Model model) {
        List<Dept> deptList = empService.getAllDept ( );
        model.addAttribute ( "deptList" , deptList );
        return "emp/empAdd";
    }

    //根据id查询员工信息
    @GetMapping("emp/{id}")
    public String selectEmpById (@PathVariable("id") int id , Model model) {
        Emp emp = empService.selectEmpById ( id );
        model.addAttribute ( "emp" , emp );
        model.addAttribute ( "deptList" , empService.getAllDept ( ) );
        return "emp/empUpdate";
    }
    //根据id查询基本信息
    @GetMapping("/user/account")
    public String getUser (Model model , HttpServletRequest request) {
        int id = (int) request.getSession ( ).getAttribute ( "userId" );
        User user = userService.selectUserById ( id );
        if ( StringUtils.isEmpty ( user.getPickName ( ) ) ||
                StringUtils.isEmpty ( user.getName ( ) ) ||
                StringUtils.isEmpty ( user.getSex ( ) ) ||
                StringUtils.isEmpty ( user.getAge ( ) ) ||
                StringUtils.isEmpty ( user.getCard ( ) ) ||
                StringUtils.isEmpty ( user.getNumber ( ) ) ||
                StringUtils.isEmpty ( user.getEmail ( ) ) ||
                StringUtils.isEmpty ( user.getProId ( ) ) ) {
            model.addAttribute ( "msg" , "您的信息尚未完善" );
            model.addAttribute ( "proList" , userService.getAllPro ( ) );
            return "user/user-setting-new";
        }
        String email = user.getEmail ( );
        String emailEncrypt = email.replaceAll ( email.substring ( 4 , email.lastIndexOf ( "@" ) ) , "*****" );
        user.setEmail ( emailEncrypt );
        model.addAttribute ( "accountList" , user );
        model.addAttribute ( "proList" , userService.getAllPro ( ) );
        return "user/user-setting";
    }

    //退出登录
    @GetMapping("/logout")
    @ResponseBody
    public Result<Object> signOut (HttpSession session) {
        session.removeAttribute ( "loginUsername" );
        return Result.success ( "您已成功退出登录" );
    }

    //跳转到注销页面
    @GetMapping("/out")
    public String logout ( ) {
        return "user/logout";
    }

    //验证码
    @RequestMapping("/captcha")
    public void captcha (HttpServletRequest request , HttpServletResponse response) throws Exception {
        CaptchaUtil.out ( request , response );
    }
}
