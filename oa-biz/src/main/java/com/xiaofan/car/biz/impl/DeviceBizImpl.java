package com.xiaofan.car.biz.impl;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.DeviceBiz;
import com.xiaofan.car.persistence.param.DeviceInfoParam;
import com.xiaofan.car.persistence.param.DeviceParam;
import com.xiaofan.car.persistence.vo.DeviceInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/10/15
 * @since 1.0.0
 */
@Service
@Slf4j
public class DeviceBizImpl implements DeviceBiz {

    /**
     * 查询设备列表
     * @param deviceParam
     * @return
     */
    @Override
    public PageInfo<DeviceInfoVo> getDeviceList(DeviceParam deviceParam) {
        // 1.
        return null;
    }

    /**
     * 查询设备详情
     * @param deviceId
     * @return
     */
    @Override
    public DeviceInfoVo getDeviceDetail(int deviceId) {
        return null;
    }

    /**
     * 新增设备信息
     * @param deviceInfoParam
     * @return
     */
    @Override
    public DeviceInfoVo addDevice(DeviceInfoParam deviceInfoParam) {
        return null;
    }

    /**
     * 删除设备信息
     * @param deviceId
     */
    @Override
    public void deleteDevice(int deviceId) {

    }

    /**
     * 更新设备信息
     * @param deviceInfoParam
     */
    @Override
    public void updateDevice(DeviceInfoParam deviceInfoParam) {

    }
}
