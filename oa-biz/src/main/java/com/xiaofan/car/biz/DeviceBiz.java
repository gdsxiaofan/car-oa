package com.xiaofan.car.biz;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.persistence.param.DeviceInfoParam;
import com.xiaofan.car.persistence.param.DeviceParam;
import com.xiaofan.car.persistence.vo.DeviceInfoVo;

/**
 * 设备管理业务层
 *
 * @author gongdaoshun
 * @date 2017/10/15
 * @since 1.0.0
 */
public interface DeviceBiz {

    /**
     * 查询设备列表
     * @param deviceParam
     * @return
     */
    public PageInfo<DeviceInfoVo> getDeviceList(DeviceParam deviceParam);

    /**
     * 查询设备详情
     * @param deviceId
     * @return
     */
    public DeviceInfoVo getDeviceDetail(int deviceId);

    /**
     * 新增设备信息
     * @param deviceInfoParam
     * @return
     */
    public DeviceInfoVo addDevice(DeviceInfoParam deviceInfoParam);

    /**
     * 删除设备信息
     * @param deviceId
     */
    public void deleteDevice(int deviceId);

    /**
     * 更新设备信息
     * @param deviceInfoParam
     */
    public void updateDevice(DeviceInfoParam deviceInfoParam);
}
