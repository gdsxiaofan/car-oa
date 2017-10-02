package com.xiaofan.car.dao.test;

import com.xiaofan.car.biz.ServiceBiz;
import com.xiaofan.car.persistence.model.ServiceInfo;
import com.xiaofan.car.persistence.param.ServiceInfoParam;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}
