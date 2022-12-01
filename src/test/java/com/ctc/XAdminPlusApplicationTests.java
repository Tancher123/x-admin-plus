package com.ctc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctc.emp.mapper.EmpMapper;
import com.ctc.emp.pojo.Emp;
import com.ctc.emp.service.impl.EmpServiceImpl;
import com.ctc.user.mapper.UserMapper;
import com.ctc.user.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class XAdminPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private EmpMapper empMapper;

    @Test
    void insert ( ) {

        User user = new User ( );

        user.setUsername ( "test" );
        user.setPassword ( "123" );
        user.setChName ( "普通用户" );
        int insert = userMapper.insert ( user );

        System.out.println ( insert );


    }

    @Test
    void delete ( ) {

        int i = userMapper.deleteById ( 2 );

        System.out.println ( i );

    }
  @Test
    void deleteIds ( ) {
//        List<String[]> ids ={"1","2","3"};
//       Collection<? extends Serializable> ;
//      int i = empMapper.deleteBatchIds ( Collections.singleton ( ids ) );
//
//      System.out.println ( i );

    }

    @Test
    void update ( ) {

        User user = new User ( );
        user.setId ( 39 );
        user.setChName ( "测试数据" );

        int i = userMapper.updateById ( user );
        System.out.println ( i );

    }

//    @Test
//    void selectForPlus ( ) {
//        QueryWrapper<User> wrapper = new QueryWrapper<> ( );
//        wrapper.eq ( "username" , "test" );
//        User user = userMapper.selectOne ( wrapper );
//        System.out.println ( user );
//
//    }

    @Test
    void insertEmp ( ) {
        Emp emp = new Emp ( );
        emp.setBirthday ( new Date (  ) );

        emp.setName ( "test" );

        int insert = empMapper.insert ( emp );
        System.out.println (insert );
    }
    @Test
    void deleteEmp(){

        int i = empMapper.deleteById ( 42 );
        System.out.println (i );

    }


    @Test
    void selectEmp(){
        QueryWrapper<Emp> wrapper = new QueryWrapper ( );
        wrapper.like ( "name","张" );
        List<Emp> emps = empMapper.selectList ( wrapper );
        emps.forEach ( System.out::println );
    }




    @Autowired
    private EmpServiceImpl empService;

    @Test
    void insert1(){
        Emp emp = new Emp ( );
        emp.setName ( "test01" );
        emp.setBirthday ( new Date (  ) );
        emp.setAge (20);
        int insert = empService.insertEmp (emp );
        System.out.println (insert );
    }
 @Test
    void deleted(){
        int[] ids = {43,44};
     int insert = empService.deleteEmpByIds ( ids);
        System.out.println (insert);
    }


}
