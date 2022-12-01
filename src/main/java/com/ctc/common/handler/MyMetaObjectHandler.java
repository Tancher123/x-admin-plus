package com.ctc.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: mybatis-plus
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-22 16:17
 * @version:1.0
 **/
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    //插入时的填充策略
    @Override
    public void insertFill (MetaObject metaObject) {
        //setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        log.info ( "start insert fill....." );
        this.setFieldValByName ( "createTime", new Date (  ),metaObject );
        this.setFieldValByName ( "updateTime", new Date (  ),metaObject );
    }

    //更新时的填充策略
    @Override
    public void updateFill (MetaObject metaObject) {
        log.info ( "start update fill....." );
        this.setFieldValByName ( "updateTime", new Date (  ),metaObject );
    }
}
