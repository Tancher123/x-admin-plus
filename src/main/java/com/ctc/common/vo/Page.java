package com.ctc.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: x-admin
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-06 21:30
 * @version:1.0
 **/

@Data
public class Page implements Serializable {
    private Integer page;
    private Integer limit;

    public Long getStart(){
        return (page - 1L) * limit;
    }
}
