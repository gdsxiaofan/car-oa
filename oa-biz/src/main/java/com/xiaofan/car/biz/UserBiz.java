package com.xiaofan.car.biz;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.persistence.param.UserQueryParam;

/**
 * Created by gongdaoshun on 2017/8/26.
 */
public interface UserBiz {
    public PageInfo<Employee> queryUserList(UserQueryParam userQueryParam);
}
