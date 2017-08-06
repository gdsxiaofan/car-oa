package com.xiaofan.car.schedule.tasks;

import com.xiaofan.car.biz.LedgerInfoBiz;
import com.xiaofan.car.persistence.model.LedgerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gongdaoshun on 2017/8/6.
 */
@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    LedgerInfoBiz ledgerInfoBiz;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static Integer id =1;
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
        LedgerInfo ledgerInfo = ledgerInfoBiz.selectLedgerInfo(id);
        log.info("当前查询数据为：{}",ledgerInfo.toString());
        id++;
    }

}
