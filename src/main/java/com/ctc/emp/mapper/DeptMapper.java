package com.ctc.emp.mapper;

import com.ctc.emp.pojo.Dept;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMapper {
    /**
     * @param :
     * @return List<Dept>
     * @author 陈天赐
     * @description 查询所有部门
     * @date 2022/11/7 13:30
     */
    @Select( "select * from dept" )
    List<Dept> getAllDept ( );
}
