package com.xiaofan.car.biz.impl;

import com.xiaofan.car.biz.LedgerInfoBiz;
import com.xiaofan.car.persistence.model.LedgerInfo;
import com.xiaofan.car.persistence.param.LedgerInfoParam;
import com.xiaofan.car.service.LedgerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Created by gongdaoshun on 2017/8/5.
 */
@Service
@Slf4j
public class LedgerInfoBizImpl implements LedgerInfoBiz{
    @Autowired
    private LedgerInfoService ledgerInfoService;

    @Override
    public Integer applyLedgerInfo(LedgerInfoParam ledgerInfoParam) {
        LedgerInfo ledgerInfo = new LedgerInfo();
        ledgerInfo.setCreateUser(ledgerInfoParam.getCreateUser());
        ledgerInfo.setDeviceNo(ledgerInfoParam.getDeviceNo());
        ledgerInfo.setCreateTime(Calendar.getInstance().getTime());

        log.info("当前时间为：{}",ledgerInfo.getCreateTime());

        return ledgerInfoService.saveLedgerInfoService(ledgerInfo);
    }
}
