package com.ctc.emp.service;

import com.ctc.emp.pojo.Dept;
import com.ctc.emp.pojo.Emp;
import com.ctc.emp.vo.EmpQuery;

import java.util.List;

public interface EmpService {
    /**
     * @param params:
     * @return List<Emp>
     * @author 陈天赐
     * @description 分页查询
     * @date 2022/11/30 21:26
     */
    List<Emp> getEmpList (EmpQuery params);

    /**
     * @param params:
     * @return Long
     * @author 陈天赐
     * @description 查询总条数
     * @date 2022/11/30 21:26
     */
    Long countEmpList (EmpQuery params);

    /**
     * @param :
     * @return List<Dept>
     * @author 陈天赐
     * @description 查询所有部门
     * @date 2022/11/30 21:26
     */
    List<Dept> getAllDept ( );

    /**
     * @param emp:
     * @return int
     * @author 陈天赐
     * @description 新增员工
     * @date 2022/11/30 21:27
     */
    int insertEmp (Emp emp);

    /**
     * @param ids:
     * @return int
     * @author 陈天赐
     * @description 根据id批量删除
     * @date 2022/11/30 21:27
     */
    int deleteEmpByIds (int[] ids);

    /**
     * @param emp:
     * @return int
     * @author 陈天赐
     * @description 修改员工
     * @date 2022/11/30 21:27
     */
    int updateEmp (Emp emp);

    /**
     * @param id:
     * @return Emp
     * @author 陈天赐
     * @description 根据id查询员工信息
     * @date 2022/11/30 21:28
     */
    Emp selectEmpById (int id);

    /**
     * @param params:
     * @return List<Emp>
     * @author 陈天赐
     * @description 查询被删除的员工
     * @date 2022/12/1 8:50
     */
    List<Emp> selectEmpByDeleted (EmpQuery params);

    /**
     * @param params:
     * @return Long
     * @author 陈天赐
     * @description 查询被删除的总条数
     * @date 2022/11/30 21:26
     */
    Long countEmpListDeleted (EmpQuery params);

    /**
     * @param :
     * @return int
     * @author 陈天赐
     * @description 通过id修改
     * @date 2022/12/1 9:27
     */
    int updateEmpDeleted (List ids);

    /**
     * @param ids:
     * @return int
     * @author 陈天赐
     * @description 批量永久删除
     * @date 2022/12/1 10:19
     */
    int deleteEmpDelByIds (int[] ids);

    /**
     * @param ids:
     * @param userName:
     * @return int
     * @author 陈天赐
     * @description 存入操作删除的用户名
     * @date 2022/12/1 13:27
     */
    int updateEmpUsernameByIds (int[] ids , String userName);

}
