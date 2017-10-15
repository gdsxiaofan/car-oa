package com.xiaofan.car.dao.repository;

import com.xiaofan.car.persistence.model.CheckInfo;
import com.xiaofan.car.persistence.vo.CheckInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 
 * CheckInfoMapper数据库操作接口类
 * 
 **/

public interface CheckInfoMapper{

    /**
     * 根据设备id查询对应的检查项信息
     * @return
     */
    public List<CheckInfoVo> getCheckInfoByDeviceId(
            @Param("deviceId")Integer deviceId
    );
}