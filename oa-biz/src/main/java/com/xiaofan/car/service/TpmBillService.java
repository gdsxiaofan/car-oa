package com.xiaofan.car.service;

import com.xiaofan.car.persistence.enumType.TmpStatusEnum;
import com.xiaofan.car.persistence.model.CheckInfo;
import com.xiaofan.car.persistence.model.TpmBill;
import com.xiaofan.car.persistence.param.TpmBillParam;

import java.util.List;

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

    public void addTpmBill(TpmBillParam tpmBillParam);

    /**
     * 新增工单
     * @param checkInfoList
     */
    public void addTpmBill(List<CheckInfo> checkInfoList);
}
