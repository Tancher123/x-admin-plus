package com.ctc.emp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctc.emp.pojo.Emp;
import com.ctc.emp.vo.EmpQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpMapper extends BaseMapper<Emp> {
    /**
     * @param params:
     * @return List<Emp>
     * @author 陈天赐
     * @description 分页查询所有员工信息
     * @date 2022/11/7 11:25
     */
    List<Emp> getEmpList (EmpQuery params);

    /**
     * @param params:
     * @return Long
     * @author 陈天赐
     * @description 获取数据的总数
     * @date 2022/11/7 11:25
     */
    Long countEmpList (EmpQuery params);

    /**
     * @param :
     * @return List<User>
     * @author 陈天赐
     * @description 查看被删除的用户
     * @date 2022/12/1 8:44
     */
    List<Emp> selectEmpByDeleted(EmpQuery params);

    /**
     * @param params:
     * @return Long
     * @author 陈天赐
     * @description 获取数据的总数
     * @date 2022/11/7 11:25
     */
    Long countEmpListDeleted (EmpQuery params);

    /**
     * @param :
      * @return int
     * @author 陈天赐
     * @description 通过id修改
     * @date 2022/12/1 9:27
     */
    int updateEmpDeleted(List ids);

    /**
     * @param ids:
      * @return int
     * @author 陈天赐
     * @description 永久删除
     * @date 2022/12/1 10:18
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
