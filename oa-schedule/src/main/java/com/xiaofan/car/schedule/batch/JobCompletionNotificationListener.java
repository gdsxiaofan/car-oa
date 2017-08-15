package com.xiaofan.car.schedule.batch;


import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * 监听任务
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("afterBatch");
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("beforeBatch");
    }
}
