package com.xiaofan.car.dao.repository;

import com.xiaofan.car.persistence.model.ServiceInfo;
import com.xiaofan.car.persistence.vo.ServiceInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 
 * ServiceInfoMapper数据库操作接口类
 * 
 **/
@Repository
public interface ServiceInfoMapper{

    public List<ServiceInfoVo> getServiceInfoList(
            @Param("deviceId") int deviceId
    );

    public ServiceInfoVo getServiceDetail(
            @Param("serviceId")Integer serviceId
    );

    public Integer saveServiceInfo(ServiceInfo serviceInfo);

    public Integer updateServiceInfo(ServiceInfo serviceInfo);

    public Integer deleteByPrimaryKey(
            @Param("id")Integer id
    );
}