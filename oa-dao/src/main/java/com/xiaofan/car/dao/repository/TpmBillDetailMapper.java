package com.xiaofan.car.dao.repository;

import com.xiaofan.car.persistence.model.TpmBill;
import com.xiaofan.car.persistence.model.TpmBillDetail;


/**
 * 
 * TpmBillDetailMapper数据库操作接口类
 * 
 **/

public interface TpmBillDetailMapper{

    /**
     * 保存对应的工单详细信息
     * @param tpmBill
     * @return
     */
    public Integer insertSelective(TpmBillDetail tpmBillDetail);
}