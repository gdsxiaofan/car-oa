package com.xiaofan.car.biz;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.persistence.param.UserQueryParam;
import com.xiaofan.car.persistence.vo.UserPermissionVo;

/**
 * Created by gongdaoshun on 2017/8/26.
 */
public interface UserBiz {
    public PageInfo<Employee> queryUserList(UserQueryParam userQueryParam);

    /**
     * 根据用户id获取当前用户信息、角色信息及菜单权限
     * @param userId
     * @return
     */
    public UserPermissionVo getUserPermisson(Integer userId);
}
