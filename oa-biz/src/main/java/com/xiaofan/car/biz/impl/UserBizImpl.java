package com.xiaofan.car.biz.impl;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.UserBiz;
import com.xiaofan.car.dao.repository.EmployeeMapper;
import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.persistence.param.UserQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gongdaoshun on 2017/8/26.
 */
@Service
@Slf4j
public class UserBizImpl implements UserBiz {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public PageInfo<Employee> queryUserList(UserQueryParam userQueryParam) {
        return null;
    }
}
