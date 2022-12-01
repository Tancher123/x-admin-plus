package com.ctc.emp.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-06 21:25
 * @version:1.0
 * Serializable:序列化
 **/
@Data
@TableName(value = "emp", resultMap = "empMap")
public class Emp implements Serializable {
    //对应数据库中的主键
    @TableId(type = IdType.AUTO)
    private Integer empId;
    private String name;
    private Integer sex; //0 : 男  1:  女
    private Integer age;
    private Double sal;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String address;
    private Integer deptId;

    private Dept dept;

    private String userName;
    @Version  //乐观锁Version注解
    private Integer version;

    @TableLogic  //逻辑删除
    private Integer deleted;

    //自动在数据库中插入创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //自动在数据库中插入修改时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
