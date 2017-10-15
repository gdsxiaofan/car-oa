package com.xiaofan.car.service;

import com.xiaofan.car.persistence.model.Device;
import com.xiaofan.car.persistence.param.DeviceInfoParam;
import com.xiaofan.car.persistence.param.DeviceParam;

/**
 * Created by gongdaoshun on 2017/10/15.
 * 设备信息的service
 */
public interface DeviceService {

    /**
     * 保存设备信息
     * @param deviceParam
     */
    public void saveDevice(DeviceInfoParam deviceParam);

    /**
     * 更新设备信息
     * @param deviceParam
     */
    public void updateDevice(DeviceInfoParam deviceParam);

    /**
     * 删除对应的设备信息
     * @param deviceId
     */
    public void deleteDevice(Integer deviceId);
}
