package com.xiaofan.car.service.impl;

import com.xiaofan.car.dao.repository.CheckInfoMapper;
import com.xiaofan.car.dao.repository.TpmBillMapper;
import com.xiaofan.car.persistence.enumType.TmpStatusEnum;
import com.xiaofan.car.persistence.enumType.TmpTypeEnum;
import com.xiaofan.car.persistence.model.CheckInfo;
import com.xiaofan.car.persistence.model.TpmBill;
import com.xiaofan.car.persistence.param.TpmBillParam;
import com.xiaofan.car.service.TpmBillService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/10/17
 * @since 1.0.0
 */
@Service
public class TpmBillServiceImpl implements TpmBillService {

    @Autowired
    private TpmBillMapper tpmBillMapper;

    @Autowired
    private CheckInfoMapper checkInfoMapper;

    @Override
    public void updateTpmBillForDeal(Integer id, TmpStatusEnum tmpStatusEnum) {

    }

    @Override
    public void updateTpmBillForAudit(Integer id, TmpStatusEnum tpmStatusEnum) {

    }

    @Override
    public void addTpmBill(TpmBillParam tpmBillParam) {

    }

    @Override
    public void addTpmBill(List<CheckInfo> checkInfoList) {
        List<TpmBill> tpmBills = null;
        if(CollectionUtils.isNotEmpty(checkInfoList)){
            tpmBills = new ArrayList<>();
            for(CheckInfo checkInfo:checkInfoList){
                TpmBill tpmBill = transformTpmBill(checkInfo);
                tpmBills.add(tpmBill);
                //计算检查项的下一次巡检日期，并且更新到对应的检查项上
                Date nextCheckDate = checkInfo.getNextCheckTime();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(nextCheckDate);
                calendar.add(Calendar.DATE,checkInfo.getCheckCycle());
                nextCheckDate = calendar.getTime();
                checkInfo.setNextCheckTime(nextCheckDate);
            }
        }
        if(tpmBills!=null){
                tpmBillMapper.insertTpmBillForList(tpmBills);
        }

        //统一更新所有的检查项信息
        checkInfoMapper.updateCheckInfo(checkInfoList);

    }

    /**
     * 根据检查项生成对应的工单
     * @param checkInfo
     * @return
     */
    private TpmBill transformTpmBill(CheckInfo checkInfo){
            TpmBill tpmBill = new TpmBill().builder()
                    .tpmName(checkInfo.getServiceName())
                    .tpmType(TmpTypeEnum.ROUTING_INSPECTION.getCode())
                    .checkId(checkInfo.getId())
                    .checkTime(checkInfo.getNextCheckTime())
                    .deviceId(checkInfo.getDeviceId())
                    .deviceName(checkInfo.getDeviceName())
                    .serviceDescribe(checkInfo.getServiceDescribe())
                    .createTime(Calendar.getInstance().getTime())
                    .build();

            return tpmBill;
    }
}
