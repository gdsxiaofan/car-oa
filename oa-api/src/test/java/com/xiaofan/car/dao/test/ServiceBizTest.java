package com.xiaofan.car.dao.test;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.ServiceBiz;
import com.xiaofan.car.persistence.model.ServiceInfo;
import com.xiaofan.car.persistence.param.ServiceInfoParam;
import com.xiaofan.car.persistence.param.ServiceParam;
import com.xiaofan.car.persistence.vo.ServiceInfoVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by gongdaoshun on 2017/10/2.
 */
public class ServiceBizTest extends BaseDaoTest{
    @Autowired
    private ServiceBiz serviceBiz;

    @Test
    public void saveServiceInfoTest(){

        ServiceInfoParam serviceInfoParam = new ServiceInfoParam().builder()
                .serviceName("测试1")
                .comment("备注")
                .property1("属性1")
                .property2("属性2")
                .property3("属性3")
                .property4("属性4")
                .comment("备注")
                .build();


        serviceBiz.saveServiceInfo(serviceInfoParam,"",1);
    }

    @Test
    public void updateServiceInfoTest(){
        ServiceInfoParam serviceInfoParam = new ServiceInfoParam().builder()
                .id(2)
                .serviceName("修改过后的测试1")
                .property1("修改过后的属性1")
                .build();
        serviceBiz.updateServiceInfo(serviceInfoParam,"",1);
    }

    public void getServiceInfoForListTest(){
        ServiceParam serviceParam = new ServiceParam().builder()
                .serviceName("修改过后的测试1")
                .pageNum(1)
                .pageSize(10)
                .build();

        PageInfo<ServiceInfoVo> serviceInfoForList = serviceBiz.getServiceInfoForList(serviceParam,"",1);
    }

}
