package com.xiaofan.car.service.impl;

import com.xiaofan.car.dao.repository.DeviceInfoMapper;
import com.xiaofan.car.persistence.model.DeviceInfo;
import com.xiaofan.car.persistence.param.DeviceInfoParam;
import com.xiaofan.car.persistence.param.DeviceParam;
import com.xiaofan.car.service.DeviceService;
import com.xiaofan.car.util.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Calendar;

/**
 * Created by gongdaoshun on 2017/10/15.
 */
@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;
    /**
     *
     * 保存对应的设备信息
     * @param deviceParam
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void saveDevice(DeviceInfoParam deviceParam) {
        // 1.转换设备信息
        DeviceInfo deviceInfo = transformDeviceInfo(deviceParam);

        // 2.设置当前时间，并保存数据
        deviceInfo.setCreateTime(Calendar.getInstance().getTime());

        deviceInfoMapper.saveDevice(deviceInfo);

    }

    /**
     * 更新对应的设备信息
     * @param deviceParam
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void updateDevice(DeviceInfoParam deviceParam) {
        // 1.转换设备信息
        Assert.notNull(deviceParam.getId(),"设备id为空");
        DeviceInfo deviceInfo = transformDeviceInfo(deviceParam);

        // 2.跟新对应的设备信息
        deviceInfoMapper.updateByPrimaryKeySelective(deviceInfo);
    }

    /**
     * 删除对应的设备信息
     * @param deviceId
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void deleteDevice(Integer deviceId) {
        // 1.设备id教研不能为空
        Assert.notNull(deviceId,"设备id不能为空");
        // 2.删除对应的设备信息
        deviceInfoMapper.deleteDevice(deviceId);
    }

    /**
     * 转换数据
     * @param deviceParam
     * @return
     */
    private DeviceInfo transformDeviceInfo(DeviceInfoParam deviceParam){
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setId(deviceParam.getId());
        deviceInfo.setArea(deviceParam.getArea());
        deviceInfo.setDeviceName(deviceParam.getDeviceName());
        deviceInfo.setLocation(deviceParam.getLocation());
        deviceInfo.setRoutingDays(deviceParam.getRoutingDays());
        deviceInfo.setFromDate(DateUtil.stringToDate(deviceParam.getFromDate(),DateUtil.SHORTFMT1));
        deviceInfo.setToDate(DateUtil.stringToDate(deviceParam.getToDate(),DateUtil.SHORTFMT1));

        return deviceInfo;
    }
}
