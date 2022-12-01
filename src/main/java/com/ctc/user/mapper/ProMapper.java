package com.ctc.user.mapper;

import com.ctc.user.pojo.Pro;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: x-admin-plus
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-30 21:31
 * @version:1.0
 **/
@Repository
public interface ProMapper {

    /**
     * @param :
      * @return List<Pro>
     * @author 陈天赐
     * @description 查询所有职业
     * @date 2022/11/30 21:33
     */
    @Select ( "select * from pro" )
    List<Pro> getAllPro();

}
