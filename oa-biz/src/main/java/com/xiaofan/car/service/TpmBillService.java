package com.xiaofan.car.service;

import com.xiaofan.car.persistence.enumType.TmpStatusEnum;

/**
 * 工单的service处理
 *
 * @author gongdaoshun
 * @date 2017/10/16
 * @since 1.0.0
 */
public interface TpmBillService {

    /**
     * 员工处理工单，更新对应工单的状态
     * @param id
     * @param tmpStatusEnum
     */
    public void updateTpmBillForDeal(Integer id, TmpStatusEnum tmpStatusEnum);

    /**
     * 组长审核对应的工单
     * @param id
     * @param tpmStatusEnum
     */
    public void updateTpmBillForAudit(Integer id,TmpStatusEnum tpmStatusEnum);
}
