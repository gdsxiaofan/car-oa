package com.xiaofan.car.dao.test;

import com.xiaofan.car.dao.repository.LedgerInfoMapper;
import com.xiaofan.car.persistence.model.LedgerInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.UUID;

public class LedgerInfoMapperTest extends BaseDaoTest {

    @Autowired
    LedgerInfoMapper ledgerInfoMapper;

    @Test
    public void inserLedgerInfoTest(){
        LedgerInfo ledgerInfo = new LedgerInfo();
        ledgerInfo.setCreateUser("β他爹");
        ledgerInfo.setDeviceNo(UUID.randomUUID().toString());
        ledgerInfo.setCreateTime(Calendar.getInstance().getTime());
        ledgerInfoMapper.insertSelective(ledgerInfo);
    }
}
