package com.xiaofan.car.dao.configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
@MapperScan("com.xiaofan.car.dao.repository")
public class MybatisConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(MybatisConfiguration.class, args);
    }
}
