package com.xiaofan.car.schedule.tasks;

import com.xiaofan.car.biz.LedgerInfoBiz;
import com.xiaofan.car.persistence.model.LedgerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gongdaoshun on 2017/8/6.
 */
//@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    private LedgerInfoBiz ledgerInfoBiz;
    @Autowired
    private Job job;
    @Autowired
    JobLauncher jobLauncher;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static Integer id =1;
    @Scheduled(fixedRate = 500000)
    public void reportCurrentTime() throws Exception{
        System.out.println("现在时间：" + dateFormat.format(new Date()));
        LedgerInfo ledgerInfo = ledgerInfoBiz.selectLedgerInfo(id);
        log.info("当前查询数据为：{}",ledgerInfo.toString());

        //传入参数
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        //执行任务
        jobLauncher.run(job,jobParameters);
        id++;
    }

}
