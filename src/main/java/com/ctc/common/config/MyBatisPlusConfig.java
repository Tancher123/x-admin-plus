package com.ctc.common.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: mybatis-plus
 * @description:
 * @author: 陈天赐
 * @create: 2022-11-22 17:01
 * @version:1.0
 **/
//扫描mapper文件夹
@MapperScan("com.ctc.*.mapper") //交给mybatis做的，可以让这个配置类做扫描
@EnableTransactionManagement //自动事务管理
@Configuration
public class MyBatisPlusConfig {
    //乐观锁
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    //逻辑删除
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector ();
    }

    //性能分析插件
    @Bean
    @Profile({"dev","test"})//设置dev开发、test测试 环境开启  保证我们的效率
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(100);//设置sql最大执行时间*ms，如果超过了则不执行
        performanceInterceptor.setFormat(true);//开启sql格式化
        return performanceInterceptor;
    }
}
