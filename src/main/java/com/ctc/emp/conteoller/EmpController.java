package com.ctc.emp.conteoller;


import com.ctc.common.vo.Result;
import com.ctc.emp.pojo.Dept;
import com.ctc.emp.pojo.Emp;
import com.ctc.emp.service.impl.EmpServiceImpl;
import com.ctc.emp.vo.EmpQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @program: x-admin-plus
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-30 18:47
 * @version:1.0
 **/
@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpServiceImpl empService;

    //分页查询，模糊查询
    @GetMapping("/list")
    public Result<Object> getEmpList (EmpQuery params) {
        List<Emp> list = empService.getEmpList ( params );//分页查询所有员工信息
        Long count = empService.countEmpList ( params );//总条数
        return Result.success ( list , count );
    }

    //分页查询被删除的员工信息
    @GetMapping("/deleted")
    public Result<Object> getEmpDeleted (EmpQuery params) {
        List<Emp> list = empService.selectEmpByDeleted ( params );//分页查询所有员工信息
        Long count = empService.countEmpListDeleted ( params );//总条数
        return Result.success ( list , count );
    }

    //增
    @PostMapping("/add.do")
    public Result<Object> insertEmp (Emp emp) {
        Date birthday = emp.getBirthday ( );
        if ( StringUtils.isEmpty ( birthday ) ) {
            return Result.fail ( "时间不能为空" );
        }
        if ( emp.getAge ( ) > 120 || emp.getAge ( ) < 0 ) {
            return Result.fail ( "您输入的年龄不合法，请确认后再输入！" );
        }
        int i = empService.insertEmp ( emp );
        if ( i > 0 ) {
            return Result.success ( "添加成功！" );
        }
        return Result.fail ( "添加失败,请联系管理员！" );
    }

    //删
    //批量删除员工
    @DeleteMapping("/{ids}")
    public Result<Object> deleteEmpByIds (@PathVariable("ids") List<Integer> ids , HttpServletRequest request) {
        Object loginUsername = request.getSession ( ).getAttribute ( "loginUsername" );

        int i = empService.deleteEmpByIds ( ids );
        int username = empService.updateEmpUsernameByIds ( ids , (String) loginUsername );
        if ( i > 0 && username>0) {
            return Result.success ( "删除成功！" );
        }
        return Result.fail ( "删除失败！" );
    }

    //改
    //修改员工
    @PutMapping("")
    public Result<Object> updateEmp (Emp emp , HttpServletRequest request) {
        Object loginChName = request.getSession ( ).getAttribute ( "loginChName" );

        if ( loginChName.equals ( "普通用户" ) ) {
            return Result.fail ( "您没权限，请联系管理员！" );
        }

        if ( emp.getAge ( ) > 120 || emp.getAge ( ) < 0 ) {
            return Result.fail ( "您输入的年龄不合法，请确认后再输入！" );
        }

        int i = empService.updateEmp ( emp );
        if ( i > 0 ) {
            return Result.success ( "员工信息修改成功！" );
        }
        return Result.fail ( "员工信息修改失败,请联系管理员！" );
    }

    //恢复员工
    @PutMapping("/recover/{ids}")
    public Result<Object> updateEmpDeleted (@PathVariable("ids") List<Integer> ids){
        int i = empService.updateEmpDeleted ( ids );
        if ( i > 0 ) {
            return Result.success ( "员工信息恢复成功！" );
        }
        return Result.fail ( "员工信息恢复失败,请联系管理员！" );
    }

    //批量永久删除员工
    @DeleteMapping("del/{ids}")
    public Result<Object> deleteEmpDelByIds (@PathVariable("ids") int[] ids ,
                                             HttpServletRequest request) {
        Object loginChName = request.getSession ( ).getAttribute ( "loginChName" );
        if ( loginChName.equals ( "普通用户" )) {
            return Result.fail ( "您没权限，请联系管理员！" );
        }
        int i = empService.deleteEmpDelByIds ( ids );
        if ( i > 0 ) {
            return Result.success ( "您已成功永久删除！" );
        }
        return Result.fail ( "删除失败！" );
    }

}
