package com.ctc.user.service;


import com.ctc.user.pojo.Pro;
import com.ctc.user.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * @param :
     * @return void
     * @author 陈天赐
     * @description 登录
     * @date 2022/11/29 22:15
     */
    User login (User user);

    /**
     * @param user:
     * @return int
     * @author 陈天赐
     * @description 注册
     * @date 2022/11/30 21:24
     */
    int register (User user);

    /**
     * @param :
     * @return List<Pro>
     * @author 陈天赐
     * @description 查询所有职业
     * @date 2022/11/30 21:26
     */
    List<Pro> getAllPro ( );

    /**
     * @param id:
      * @return int
     * @author 陈天赐
     * @description 用户注销
     * @date 2022/11/30 21:29
     */
    int deleteUserById(int id);

    /**
     * @param user:
      * @return int
     * @author 陈天赐
     * @description 修改以用户信息
     * @date 2022/11/30 21:30
     */
    int updateUser (User user);

    /**
     * @param id:
      * @return User
     * @author 陈天赐
     * @description 根据id查询信息
     * @date 2022/11/30 21:40
     */
    User selectUserById(int id);

    /**
     * @param name:
      * @return User
     * @author 陈天赐
     * @description 根据用户名查找
     * @date 2022/11/30 22:48
     */
    User selectUserByUsername(String username);
}
