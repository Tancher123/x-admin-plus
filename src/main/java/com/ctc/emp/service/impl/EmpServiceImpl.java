package com.ctc.emp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctc.emp.mapper.DeptMapper;
import com.ctc.emp.mapper.EmpMapper;
import com.ctc.emp.pojo.Dept;
import com.ctc.emp.pojo.Emp;
import com.ctc.emp.service.EmpService;
import com.ctc.emp.vo.EmpQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: x-admin-plus
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-30 18:49
 * @version:1.0
 **/
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptMapper deptMapper;
    /**
     * @param params:
     * @return List<Emp>
     * @author 陈天赐
     * @description 分页查询
     * @date 2022/11/30 21:26
     */
    @Override
    public List<Emp> getEmpList (EmpQuery params) {
        return empMapper.getEmpList ( params );
    }
    /**
     * @param params:
     * @return Long
     * @author 陈天赐
     * @description 查询总条数
     * @date 2022/11/30 21:26
     */
    @Override
    public Long countEmpList (EmpQuery params) {
        return empMapper.countEmpList ( params );
    }
    /**
     * @param :
     * @return List<Dept>
     * @author 陈天赐
     * @description 查询所有部门
     * @date 2022/11/30 21:26
     */
    @Override
    public List<Dept> getAllDept ( ) {
        return deptMapper.getAllDept ( );
    }
    /**
     * @param emp:
     * @return int
     * @author 陈天赐
     * @description 新增员工
     * @date 2022/11/30 21:27
     */
    @Override
    public int insertEmp (Emp emp) {
        int i = empMapper.insert ( emp );
        return i;
    }
    /**
     * @param ids:
     * @return int
     * @author 陈天赐
     * @description 根据id批量删除
     * @date 2022/11/30 21:27
     */
    @Override
    public int deleteEmpByIds (List<Integer> ids) {
        int i = empMapper.deleteBatchIds ( ids );
        return i;
    }
    /**
     * @param emp:
     * @return int
     * @author 陈天赐
     * @description 修改员工
     * @date 2022/11/30 21:27
     */
    @Override
    public int updateEmp (Emp emp) {
        int i = empMapper.updateById ( emp );
        return i;
    }
    /**
     * @param id:
     * @return Emp
     * @author 陈天赐
     * @description 根据id查询员工信息
     * @date 2022/11/30 21:28
     */
    @Override
    public Emp selectEmpById (int id) {
        Emp emp = empMapper.selectById ( id );
        return emp;
    }
    /**
     * @param params:
     * @return List<Emp>
     * @author 陈天赐
     * @description 查询被删除的员工
     * @date 2022/12/1 8:50
     */
    @Override
    public List<Emp> selectEmpByDeleted (EmpQuery params) {
        List<Emp> empList = empMapper.selectEmpByDeleted ( params );
        return empList;
    }
    /**
     * @param params:
     * @return Long
     * @author 陈天赐
     * @description 查询被删除的总条数
     * @date 2022/11/30 21:26
     */
    @Override
    public Long countEmpListDeleted (EmpQuery params) {
        return empMapper.countEmpListDeleted ( params );
    }
    /**
     * @param :
     * @return int
     * @author 陈天赐
     * @description 通过id修改
     * @date 2022/12/1 9:27
     */
    @Override
    public int updateEmpDeleted (List<Integer> ids) {
        int i = empMapper.updateEmpDeleted ( ids );
        return i;
    }
    /**
     * @param ids:
     * @return int
     * @author 陈天赐
     * @description 批量永久删除
     * @date 2022/12/1 10:19
     */
    @Override
    public int deleteEmpDelByIds (int[] ids) {
        int i = empMapper.deleteEmpDelByIds ( ids );
        return i;
    }

    @Override
    public int updateEmpUsernameByIds (List<Integer> ids , String username) {
        int i = empMapper.updateEmpUsernameByIds ( ids , username );
        return i;
    }
}
