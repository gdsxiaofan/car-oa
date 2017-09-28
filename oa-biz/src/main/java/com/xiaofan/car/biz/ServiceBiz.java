package com.xiaofan.car.biz;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.persistence.param.ServiceInfoParam;
import com.xiaofan.car.persistence.param.ServiceParam;
import com.xiaofan.car.persistence.vo.ServiceInfoVo;

import java.util.List;

/**
 * 服务管理相关所有的业务逻辑处理接口定义
 *
 * @author gongdaoshun
 * @date 2017/9/28
 * @since 1.0.0
 */
public interface ServiceBiz {

    /**
     * 查询设备的列表信息
     * @param serviceParam
     * @param userName
     * @param userId
     * @return
     */
    public PageInfo<ServiceInfoVo> getServiceInfoForList(ServiceParam serviceParam, String userName, Integer userId);

    /**
     * 获取设备的详细信息
     * @param serviceId
     * @param userName
     * @param userId
     * @return
     */
    public ServiceInfoVo getServiceInfoVoDetail(Integer serviceId,String userName,Integer userId);

    /**
     *
     * @param serviceInfoParam
     * @param userName
     * @param userId
     * @return
     */
    public boolean updateServiceInfo(ServiceInfoParam serviceInfoParam,String userName,Integer userId);

    /**
     * 保存服务的信息
     * @param serviceInfoParam
     * @param userName
     * @param userId
     * @return
     */
    public boolean saveServiceInfo(ServiceInfoParam serviceInfoParam, String userName, Integer userId);

    /**
     * 删除对应的服务信息
     * @param serviceId
     * @param userName
     * @param userId
     * @return
     */
    public boolean deleteServiceInfo(Integer serviceId,String userName,Integer userId);
}
