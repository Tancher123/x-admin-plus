package com.ctc.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: x-admin-plus
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-29 21:03
 * @version:1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pro implements Serializable {

    private Integer proId;
    private String proName;

}
