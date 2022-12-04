package com.ctc.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctc.user.mapper.ProMapper;
import com.ctc.user.mapper.UserMapper;
import com.ctc.user.pojo.Pro;
import com.ctc.user.pojo.User;
import com.ctc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: x-admin-plus
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-29 22:16
 * @version:1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProMapper proMapper;

    @Override
    public User login (User user) {
        QueryWrapper<User> wrapper = new QueryWrapper ( );
        wrapper.eq ( "username" , user.getUsername ( ) );
        User login = userMapper.selectOne ( wrapper );
        return login;
    }

    @Override
    public int register (User user) {
        return userMapper.insert ( user );
    }

    @Override
    public List<Pro> getAllPro ( ) {
        List<Pro> pro = proMapper.getAllPro ( );
        return pro;
    }

    @Override
    public int deleteUserById (int id) {
        int i = userMapper.deleteById ( id );
        return i;
    }

    @Override
    public int updateUser (User user) {
        int i = userMapper.updateById ( user );
        return i;
    }

    @Override
    public User selectUserById (int id) {
        User user = userMapper.selectById ( id );
        return user;
    }

    @Override
    public User selectUserByUsername (String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ( );
        wrapper.eq ( "username" , username );
        User user = userMapper.selectOne ( wrapper );
        return user;
    }

    @Override
    public User selectUserByEmail (String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ( );
        wrapper.eq ( "email" , email );
        User user = userMapper.selectOne ( wrapper );
        return user;
    }

    @Override
    public User selectUserByNumber (String number) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ( );
        wrapper.eq ( "number" , number );
        User user = userMapper.selectOne ( wrapper );
        return user;
    }

    @Override
    public User selectUserByCard (String card) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ( );
        wrapper.eq ( "card" , card );
        User user = userMapper.selectOne ( wrapper );
        return user;
    }
}
