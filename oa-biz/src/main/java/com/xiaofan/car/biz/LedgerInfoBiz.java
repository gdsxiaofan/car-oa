package com.xiaofan.car.biz;

import com.xiaofan.car.persistence.model.LedgerInfo;
import com.xiaofan.car.persistence.param.LedgerInfoParam;

/**
 * Created by gongdaoshun on 2017/8/5.
 */
public interface LedgerInfoBiz {

    public Integer applyLedgerInfo(LedgerInfoParam ledgerInfoParam);
}
