package com.xiaofan.car.dao.repository;

import com.xiaofan.car.persistence.model.DeviceInfo;
import com.xiaofan.car.persistence.param.DeviceParam;
import com.xiaofan.car.persistence.vo.DeviceInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 
 * DeviceInfoMapper数据库操作接口类
 * 
 **/
@Repository
public interface DeviceInfoMapper{

    public List<DeviceInfoVo> selectDeviceList(
        @Param("deviceName")String deviceName
    );

    public DeviceInfoVo selectDeviceById(
            @Param("deviceId")int deviceId
    );

    public Integer saveDevice(DeviceInfo deviceInfo);

    public Integer updateByPrimaryKeySelective(DeviceInfo deviceInfo);

    public Integer deleteDevice(
            @Param("id")Integer id
    );
}