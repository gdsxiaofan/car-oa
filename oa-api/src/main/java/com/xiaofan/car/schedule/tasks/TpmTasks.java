package com.xiaofan.car.schedule.tasks;

import com.xiaofan.car.job.TpmJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by gongdaoshun on 2017/10/28.
 */
@Component
@Slf4j
public class TpmTasks {

    @Autowired
    private TpmJob tpmJob;

    @Scheduled(cron="0 0/1 * * * ?")
    public void createTmp() throws Exception{
        tpmJob.exeu();
    }
}
