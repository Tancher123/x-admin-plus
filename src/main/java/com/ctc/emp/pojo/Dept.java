package com.ctc.emp.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-07 09:53
 * @version:1.0
 **/
@Data
public class Dept implements Serializable {
    private Integer deptId;
    private String deptName;
}
