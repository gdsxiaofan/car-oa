package com.xiaofan.car.biz.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.ServiceBiz;
import com.xiaofan.car.dao.repository.ServiceInfoMapper;
import com.xiaofan.car.persistence.model.ServiceInfo;
import com.xiaofan.car.persistence.param.ServiceInfoParam;
import com.xiaofan.car.persistence.param.ServiceParam;
import com.xiaofan.car.persistence.vo.ServiceInfoVo;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 服务管理相关所有的业务逻辑处理接口实现
 *
 * @author gongdaoshun
 * @date 2017/9/28
 * @since 1.0.0
 */
@Service
@Slf4j
public class ServiceBizImpl implements ServiceBiz {

    @Autowired
    private ServiceInfoMapper serviceInfoMapper;

    @Override
    public PageInfo<ServiceInfoVo> getServiceInfoForList(ServiceParam serviceParam, String userName, Integer userId) {
        //1.处理查询参数
        int pageNum = serviceParam.getPageNum()==0?1:serviceParam.getPageNum();
        int pageSize = serviceParam.getPageSize()==0?10:serviceParam.getPageSize();

        //2设置分页参数查询对应的数据
        PageHelper.startPage(pageNum,pageSize);
        List<ServiceInfoVo> serviceInfoVos = serviceInfoMapper.getServiceInfoList(serviceParam.getServiceName());

        //3.处理返回参数
        if(CollectionUtils.isEmpty(serviceInfoVos)){
            return null;
        }
        PageInfo<ServiceInfoVo> serviceInfoPageInfo = new PageInfo<>(serviceInfoVos);
        return serviceInfoPageInfo;
    }

    @Override
    public ServiceInfoVo getServiceInfoVoDetail(Integer serviceId, String userName, Integer userId) {

        // 1.根据serviceId获取当前服务的详细信息
        ServiceInfoVo serviceInfoVo = serviceInfoMapper.getServiceDetail(serviceId);
        return serviceInfoVo;
    }

    @Override
    public boolean updateServiceInfo(ServiceInfoParam serviceInfoParam, String userName, Integer userId) {
        //1.校验参数
        Assert.notNull(serviceInfoParam.getId(),"当前服务id不能为空");

        //2.处理参数
        ServiceInfo serviceInfo = new ServiceInfo().builder()
                .id(serviceInfoParam.getId())
                .serviceDescription(serviceInfoParam.getComment())
                .serviceName(serviceInfoParam.getServiceName())
                .property1(serviceInfoParam.getProperty1())
                .property2(serviceInfoParam.getProperty2())
                .property3(serviceInfoParam.getProperty3())
                .property4(serviceInfoParam.getProperty4())
                .build();

        Integer count = serviceInfoMapper.updateServiceInfo(serviceInfo);
        if(count ==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean saveServiceInfo(ServiceInfoParam serviceInfoParam, String userName, Integer userId){

        //1.处理参数
        ServiceInfo serviceInfo = ServiceInfo.builder()
                .id(serviceInfoParam.getId())
                .serviceDescription(serviceInfoParam.getComment())
                .serviceName(serviceInfoParam.getServiceName())
                .property1(serviceInfoParam.getProperty1())
                .property2(serviceInfoParam.getProperty2())
                .property3(serviceInfoParam.getProperty3())
                .property4(serviceInfoParam.getProperty4())
                .build();
        // 2.保存数据
        Integer count = serviceInfoMapper.saveServiceInfo(serviceInfo);
        return true;
    }

    @Override
    public boolean deleteServiceInfo(Integer serviceId, String userName, Integer userId) {

        // 1.校验服务的id
         Assert.notNull(serviceId,"当前服务id不能为空");

         // 2.删除对应的数据
        serviceInfoMapper.deleteByPrimaryKey(serviceId);
        return true;
    }
}
