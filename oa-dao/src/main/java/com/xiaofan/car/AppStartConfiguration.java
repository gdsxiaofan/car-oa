package com.xiaofan.car;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by gongdaoshun on 2017/8/6.
 */
@SpringBootApplication
@MapperScan("com.xiaofan.car.dao.repository")
public class AppStartConfiguration{

    public static  void main(String args[]){
        SpringApplication.run(AppStartConfiguration.class,args);
    }
}
