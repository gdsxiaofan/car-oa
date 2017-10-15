package com.xiaofan.car.biz.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.DeviceBiz;
import com.xiaofan.car.dao.repository.CheckInfoMapper;
import com.xiaofan.car.dao.repository.DeviceInfoMapper;
import com.xiaofan.car.persistence.model.DeviceInfo;
import com.xiaofan.car.persistence.param.DeviceInfoParam;
import com.xiaofan.car.persistence.param.DeviceParam;
import com.xiaofan.car.persistence.vo.CheckInfoVo;
import com.xiaofan.car.persistence.vo.DeviceInfoVo;
import com.xiaofan.car.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

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

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    @Autowired
    private CheckInfoMapper checkInfoMapper;

    @Autowired
    private DeviceService deviceService;

    /**
     * 查询设备列表
     * @param deviceParam
     * @return
     */
    @Override
    public PageInfo<DeviceInfoVo> getDeviceList(DeviceParam deviceParam) {
        // 1.设置分页参数
        PageHelper.startPage(deviceParam.getPageNum(),deviceParam.getPageSize());
        // 2.查询对应的设备列表数据
        List<DeviceInfoVo> deviceInfoVoList = deviceInfoMapper.selectDeviceList(deviceParam.getDeviceName());

        if(CollectionUtils.isEmpty(deviceInfoVoList)){
            return null;
        }
        // 3.处理返回数据
        PageInfo<DeviceInfoVo> returnVo = new PageInfo<>(deviceInfoVoList);
        return returnVo;
    }

    /**
     * 查询设备详情
     * @param deviceId
     * @return
     */
    @Override
    public DeviceInfoVo getDeviceDetail(Integer deviceId) {
        // 1.校验参数
        Assert.notNull(deviceId,"请选中对应的设备");
        // 2.查询对应的设备信息
        DeviceInfoVo deviceInfoVo = deviceInfoMapper.selectDeviceById(deviceId);
        if(deviceInfoVo==null){
            return null;
        }
        // 3.查询设备关联的检查项信息
        List<CheckInfoVo> checkInfoVos = checkInfoMapper.getCheckInfoByDeviceId(deviceId);

        deviceInfoVo.setCheckInfoVoList(checkInfoVos);

        return deviceInfoVo;
    }

    /**
     * 新增设备信息
     * @param deviceInfoParam
     * @return
     */
    @Override
    public DeviceInfoVo addDevice(DeviceInfoParam deviceInfoParam) {
        deviceService.saveDevice(deviceInfoParam);
        return null;
    }

    /**
     * 删除设备信息
     * @param deviceId
     */
    @Override
    public void deleteDevice(Integer deviceId) {
        deviceService.deleteDevice(deviceId);
    }

    /**
     * 更新设备信息
     * @param deviceInfoParam
     */
    @Override
    public void updateDevice(DeviceInfoParam deviceInfoParam) {
        deviceService.updateDevice(deviceInfoParam);
    }
}
