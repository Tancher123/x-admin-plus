package com.ctc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ctc.*.mapper")
public class XAdminPlusApplication {

    public static void main (String[] args) {
        SpringApplication.run ( XAdminPlusApplication.class , args );
    }

}
