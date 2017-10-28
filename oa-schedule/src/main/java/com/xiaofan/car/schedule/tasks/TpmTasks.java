package com.xiaofan.car.schedule.tasks;

import com.xiaofan.car.job.TpmJob;
import com.xiaofan.car.persistence.model.LedgerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gongdaoshun on 2017/10/28.
 */
@Component
@Slf4j
public class TpmTasks {

    @Autowired
    private TpmJob tpmJob;

    @Scheduled(cron="0 0 2 * * ?")
    public void createTmp() throws Exception{
        tpmJob.exeu();
    }
}
