package com.ctc.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctc.user.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-06 14:42
 * @version:1.0
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {
}
