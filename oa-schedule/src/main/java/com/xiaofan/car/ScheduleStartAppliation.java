package com.xiaofan.car;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by gongdaoshun on 2017/8/6.
 */
@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
public class ScheduleStartAppliation {
    public static void main(String[] args) {
        SpringApplication.run(ScheduleStartAppliation.class,args);
    }
}
