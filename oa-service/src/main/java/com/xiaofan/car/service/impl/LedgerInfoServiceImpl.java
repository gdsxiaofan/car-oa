package com.xiaofan.car.service.impl;

import com.xiaofan.car.dao.repository.LedgerInfoMapper;
import com.xiaofan.car.persistence.model.LedgerInfo;
import com.xiaofan.car.service.LedgerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gongdaoshun on 2017/8/5.
 */
@Service
public class LedgerInfoServiceImpl implements LedgerInfoService{

    @Autowired
    LedgerInfoMapper ledgerInfoMapper;
    @Override
    public Integer saveLedgerInfoService(LedgerInfo ledgerInfo) {

        return ledgerInfoMapper.insertSelective(ledgerInfo);
    }

    @Override
    public LedgerInfo selectLedgerInfo(Integer id) {
        return ledgerInfoMapper.selectLedgerInfoById(id);
    }
}
