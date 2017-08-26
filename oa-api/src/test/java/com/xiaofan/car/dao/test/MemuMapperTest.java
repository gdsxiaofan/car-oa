package com.xiaofan.car.dao.test;

import com.xiaofan.car.dao.repository.MemuMapper;
import com.xiaofan.car.persistence.model.Memu;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
@Rollback(value = false)
public class MemuMapperTest extends  BaseDaoTest{

    @Autowired
    MemuMapper memuMapper;

    /**
     * 初始化菜单选项
     */
    @Test
    public void insertMemuTest(){
        List<Memu> addMemu = new ArrayList<>();
        //权限管理初始化
        Memu perssionMemu = getMemu("权限管理","",1,null);
        Integer perssionParentId =memuMapper.insertSelective(perssionMemu);
        perssionParentId=perssionMemu.getId();
        Memu roleMemu = getMemu("角色管理","",2,perssionParentId);
        Memu addRoleMemu = getMemu("新增角色","",2,perssionParentId);
        Memu userRoleMemu = getMemu("用户角色管理","",2,perssionParentId);
        addMemu.add(roleMemu);
        addMemu.add(addRoleMemu);
        addMemu.add(userRoleMemu);

        //工单管理
        Memu orderMemu = getMemu("工单管理","",1,null);
        Integer orderParentId =memuMapper.insertSelective(orderMemu);
        orderParentId=orderMemu.getId();
        Memu myOrderMemu = getMemu("角色管理","",2,orderParentId);
        Memu deployOrderMemu = getMemu("新增角色","",2,orderParentId);
        Memu distributionOrderMemu = getMemu("用户角色管理","",2,orderParentId);
        Memu historyOrderRoleMemu = getMemu("新增角色","",2,orderParentId);
        Memu allOrderRoleMemu = getMemu("用户角色管理","",2,orderParentId);
        addMemu.add(myOrderMemu);
        addMemu.add(deployOrderMemu);
        addMemu.add(distributionOrderMemu);
        addMemu.add(historyOrderRoleMemu);
        addMemu.add(allOrderRoleMemu);

        //员工管理
        Memu employeeMemu = getMemu("员工管理","",1,null);
        Integer employeeParentId =memuMapper.insertSelective(employeeMemu);
        employeeParentId=employeeMemu.getId();
        Memu employeeInfoMemu = getMemu("员工信息管理","",2,employeeParentId);
        Memu addEmployeeInfoMemu = getMemu("新增员工","",2,employeeParentId);
        addMemu.add(employeeInfoMemu);
        addMemu.add(addEmployeeInfoMemu);

        //设备管理
        Memu deviceMemu = getMemu("设备管理","",1,null);
        Integer deviceParentId =memuMapper.insertSelective(deviceMemu);
        deviceParentId=deviceMemu.getId();
        Memu deviceInfoMemu = getMemu("设备基础信息管理","",2,deviceParentId);
        Memu addDeviceMemu = getMemu("新增设备信息","",2,deviceParentId);
        addMemu.add(deviceInfoMemu);
        addMemu.add(addDeviceMemu);

        addMemu(addMemu);
    }

    private Memu getMemu(String name,String href,Integer order,Integer parentId){
        Memu memu = new Memu().builder()
                .name(name)
                .href(href)
                .orderTop(order)
                .parentId(parentId)
                .build();

        return memu;
    }

    public void addMemu(List<Memu> memuList){
        for (Memu memu:memuList){
            memuMapper.insertSelective(memu);
        }
    }
}
