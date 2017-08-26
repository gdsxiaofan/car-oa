package com.xiaofan.car.biz.impl;

import com.xiaofan.car.biz.LoginBiz;
import com.xiaofan.car.dao.repository.EmployeeMapper;
import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.util.lang.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/25
 * @since 1.0.0
 */
@Slf4j
@Service
public class LoginBizImpl implements LoginBiz{

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public Employee verificationForLogin(String employeeNo, String password) {
        // 1.校验参宿
        Assert.isTrue(StringUtils.isNotBlank(employeeNo),"工号不能为空！");
        Assert.isTrue(StringUtils.isNotBlank(password),"密码不能为空！");

        // 2.密码加密处理
        password = MD5Util.encode(password);

        // 3.查询对应数据
        Employee employee = employeeMapper.selectEmployeeForLogin(employeeNo,password);

        return employee;
    }
}
