package com.xiaofan.car.job;

import com.xiaofan.car.dao.repository.CheckInfoMapper;
import com.xiaofan.car.persistence.model.CheckInfo;
import com.xiaofan.car.persistence.model.TpmBill;
import com.xiaofan.car.service.TpmBillService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by gongdaoshun on 2017/10/28.
 */
@Service
@Slf4j
public class TpmJob {

    @Autowired
    private CheckInfoMapper checkInfoMapper;

    @Autowired
    private TpmBillService tpmBillService;
    public void exeu() {
        Date today = Calendar.getInstance().getTime();
        log.info(">>>>>>>>开始生成当日所有的工单，当前时间为：", today.toString());
        //查询当天所有需要生成工单的检查项信息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(today)+" "+"00:00:00";
        String endTime = sdf.format(today)+" "+"23:59:59";
        List<CheckInfo> checkInfos = checkInfoMapper.selectTodayCheckInfo(startTime,endTime);

        if(CollectionUtils.isNotEmpty(checkInfos)){
            tpmBillService.addTpmBill(checkInfos);
        }
        log.info(">>>>>>>>结束生成当日所有的工单，当前时间为：", today.toString());
    }
}
