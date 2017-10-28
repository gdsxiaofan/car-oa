package com.xiaofan.car.biz.impl;

import com.xiaofan.car.biz.CheckInfoBiz;
import com.xiaofan.car.dao.repository.CheckInfoMapper;
import com.xiaofan.car.persistence.model.CheckInfo;
import com.xiaofan.car.persistence.param.CheckInfoParam;
import com.xiaofan.car.persistence.vo.CheckInfoVo;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 检查项相关的biz层
 *
 * @author gongdaoshun
 * @date 2017/10/16
 * @since 1.0.0
 */
@Service
@Slf4j
public class CheckInfoBizImpl implements CheckInfoBiz {

    @Autowired
    private CheckInfoMapper checkInfoMapper;

    @Override
    public List<CheckInfoVo> getCheckInfoList(Integer deviceId) {
        // 1.校验参数
        Assert.notNull(deviceId,"设备id不能为空");

        // 2. 查询对应的检查项列表
        List<CheckInfoVo> checkInfoVos = checkInfoMapper.getCheckInfoByDeviceId(deviceId);
        return checkInfoVos;
    }

    @Override
    public void addCheckInfo(CheckInfoParam checkInfoParam) {
        // 1.保存对应的额检查项信息，当前检查项的设备id不能为空
        Assert.notNull(checkInfoParam.getDeviceId(),"当前设备id不能为空");
        checkData(checkInfoParam);
        // 2.转换带保存的数据
        CheckInfo checkInfo = transformCheckInfo(checkInfoParam);
        checkInfo.setCreateTime(Calendar.getInstance().getTime());
        // 3.保存对应的检查项信息
        checkInfoMapper.insertSelective(checkInfo);

    }

    @Override
    public void updateCheckInfo(CheckInfoParam checkInfoParam) {
        // 1.校验当前检查项id
        Assert.notNull(checkInfoParam.getId(),"当前检查项id不能为空");

        // 2.转换信息，并且更新对应的数据
        CheckInfo checkInfo = transformCheckInfo(checkInfoParam);
        checkInfoMapper.updateByPrimaryKeySelective(checkInfo);
    }

    @Override
    public void deleteCheckInfo(Integer checkId) {
        // 1.校验参数
        Assert.notNull(checkId,"当前检查项id不能为空");
        // 2.更新当前记录的deleteStatus
        checkInfoMapper.deleteCheckInfoForDelete(checkId);
    }

    private CheckInfo transformCheckInfo(CheckInfoParam checkInfoParam){
        CheckInfo checkInfo = CheckInfo.builder()
                .id(checkInfoParam.getId())
                .checkComment(checkInfoParam.getCheckComment())
                .deviceId(checkInfoParam.getDeviceId())
                .checkPoint(checkInfoParam.getCheckPoint())
                .setValue(checkInfoParam.getSetValue())
                .shiftsNo(checkInfoParam.getShiftsNo())
                .systemName(checkInfoParam.getSystemName())
                .deviceName(checkInfoParam.getDeviceName())
                .serviceName(checkInfoParam.getServiceName())
                .checkCycle(checkInfoParam.getCheckCycle())
                .checkComment(checkInfoParam.getCheckComment())
                .serviceDescribe(checkInfoParam.getServiceDescribe())
                .build();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date startTime = sdf.parse(checkInfoParam.getFirstCheckTime());
            Date endTime = sdf.parse(checkInfoParam.getLastCheckTime());
            String[] checkTimeArray = checkInfoParam.getCheckTime().split(":");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startTime);
            calendar.set(Calendar.HOUR_OF_DAY,Integer.valueOf(checkTimeArray[0]));
            calendar.set(Calendar.MINUTE,Integer.valueOf(checkTimeArray[1]));
            calendar.set(Calendar.SECOND,0);
            Date nextCheckTime = calendar.getTime();
            checkInfo.setFirstCheckTime(startTime);
            checkInfo.setLastCheckTime(endTime);
            checkInfo.setNextCheckTime(nextCheckTime);


        }catch(Exception e){
            log.error("检查项录入，转换时间错误：",e);
            throw new RuntimeException("请录入准确的巡检开始时间和结束时间");
        }

        return checkInfo;
    }

    /**
     * 校验数据格式
     * @param checkInfoParam
     * @return
     */
    private void checkData(CheckInfoParam checkInfoParam){
        String checkTime = checkInfoParam.getCheckTime();

        if(!checkTime.contains(":")){
            throw new RuntimeException("检查时间格式为：‘时:分’");
        }
        String[] checkData = checkTime.split(":");
        if(Integer.valueOf(checkData[0])>23||Integer.valueOf(checkData[0])<0){
            throw new RuntimeException("检查时间小时范围为：0-23");
        }
        if(Integer.valueOf(checkData[0])>59||Integer.valueOf(checkData[0])<0){
            throw new RuntimeException("检查时间分钟范围为：0-59");
        }
    }
}
