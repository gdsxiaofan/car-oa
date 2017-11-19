package com.xiaofan.car.job;

import com.xiaofan.car.biz.ArrangeBiz;
import com.xiaofan.car.dao.repository.ArrangeInfoMapper;
import com.xiaofan.car.dao.repository.CheckInfoMapper;
import com.xiaofan.car.dao.repository.TpmBillMapper;
import com.xiaofan.car.persistence.enumType.IsEffectiveEnum;
import com.xiaofan.car.persistence.enumType.TmpStatusEnum;
import com.xiaofan.car.persistence.model.ArrangeInfo;
import com.xiaofan.car.persistence.model.CheckInfo;
import com.xiaofan.car.persistence.model.TpmBill;
import com.xiaofan.car.service.TpmBillService;
import com.xiaofan.car.util.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private ArrangeInfoMapper arrangeInfoMapper;

    @Autowired
    private TpmBillMapper tpmBillMapper;

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

    /**
     * 处理昨日的过期数据
     */
    public void updateForOverdueForLastDay(){
        Date today = Calendar.getInstance().getTime();
        log.info(">>>>>>>>处理昨日过期的工单数据,当前时间为：", today.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String endTime = sdf.format(today)+" "+"00:00:00";
        List<Integer> tpmStatus = new ArrayList<>();
        tpmStatus.add(TmpStatusEnum.PENDING.getCode());//待巡检
        tpmStatus.add(TmpStatusEnum.REPAIRING.getCode());//待维修
        tpmStatus.add(TmpStatusEnum.PENDED.getCode());
        tpmStatus.add(TmpStatusEnum.REPAIRED.getCode());
        SimpleDateFormat sdfLong = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try{
            List<TpmBill> tpmBills = tpmBillMapper.getTpmBillForOverdue(tpmStatus,null,sdfLong.parse(endTime));

            //处理所有需要转为过期的数据
            dealAllData(tpmBills);
        }catch(Exception e){
            log.error("处理昨日的过期工单失败：",e);
        }

    }

    /**
     * 处理昨日的过期数据
     */
    public void updateForOverdueForToDay() {
        Date today = Calendar.getInstance().getTime();
        log.info(">>>>>>>>处理当日过期的工单数据,当前时间为：", today.toString());
        //1.查询当前的排班设置
        ArrangeInfo arrangeInfo = arrangeInfoMapper.selectCurrentArrange(IsEffectiveEnum.EFFECTIVE.getCode());
        //处理当前时间年月日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(today)+" "+"00:00:00";
        String morningEnd = sdf.format(today) + " " + arrangeInfo.getMorningEnd();
        String noonEnd = sdf.format(today) + " " + arrangeInfo.getNoonEnd();

        /**
         * 1.如果当前时间比早班最晚时间大且比晚班最晚时间小，则查询早班未处理的工单，置为过期
         * 2.如果当前时间比晚班最晚时间大，则直接将当日所有未处理工单置为过期
         */
        try{
            SimpleDateFormat sdfLong = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            List<TpmBill> tpmBills = new ArrayList<>();
            //如果当前时间处于晚班时间，查询所有早班未处理的工单
            List<Integer> tpmStatus = new ArrayList<>();
            tpmStatus.add(TmpStatusEnum.PENDING.getCode());//待巡检
            tpmStatus.add(TmpStatusEnum.REPAIRING.getCode());//待维修
            tpmStatus.add(TmpStatusEnum.PENDED.getCode());
            if(today.getTime()>sdfLong.parse(morningEnd).getTime()
                    &&today.getTime()<sdfLong.parse(noonEnd).getTime()){
                tpmBills = tpmBillMapper.getTpmBillForOverdue(tpmStatus,sdfLong.parse(startTime),sdfLong.parse(morningEnd));
            }
            //如果当前时间是在晚班之后，则查询所有晚班的工单
            if(today.getTime()>sdfLong.parse(noonEnd).getTime()){
                tpmBills = tpmBillMapper.getTpmBillForOverdue(tpmStatus,sdfLong.parse(startTime),today);
            }

            //处理所有需要转为过期的数据
            dealAllData(tpmBills);

        }catch (Exception e){
            log.error("处理当日过期工单失败！",e);
        }
        log.info(">>>>>>>>处理当日过期的工单数据,当前时间为：", today.toString());
    }

    private void dealAllData(List<TpmBill> tpmBills){
        //处理所有需要转为过期的数据
        if(CollectionUtils.isNotEmpty(tpmBills)){
            List<TpmBill> overdueTpmBillList = new ArrayList<>();
            for(TpmBill tpmBill:tpmBills){
                Integer status = TmpStatusEnum.REPAIR_OVERDUE.getCode();
                if(tpmBill.getTpmStatus().equals(TmpStatusEnum.PENDING.getCode())){
                    status = TmpStatusEnum.PEND_OVERDUE.getCode();
                }
                if(tpmBill.getTpmStatus().equals(TmpStatusEnum.PENDED.getCode())
                        || tpmBill.getTpmStatus().equals(TmpStatusEnum.REPAIRED.getCode())){
                    status = TmpStatusEnum.AUDIT_OVERDUE.getCode();
                }
                TpmBill overdueTpmBill = new TpmBill().builder().
                        id(tpmBill.getId())
                        .tpmStatus(status)
                        .build();
                overdueTpmBillList.add(overdueTpmBill);
            }
            //更新所有的过期数据
            tpmBillMapper.updateTpmBillList(overdueTpmBillList);
        }
    }
}
