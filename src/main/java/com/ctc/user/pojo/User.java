package com.ctc.user.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-06 14:40
 * @version:1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user",resultMap = "userMap")
public class User implements Serializable {

    //对应数据库中的主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String chName;
    private String pickName;
    private String name;
    private Integer sex; // 1：女 0：男
    private Integer age;
    private String card;
    private String number;
    private String email;

    //职业
    private Integer proId;

    private Pro pro;
    @TableLogic  //逻辑删除
    private Integer deleted;

    //自动在数据库中插入创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //自动在数据库中插入修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
