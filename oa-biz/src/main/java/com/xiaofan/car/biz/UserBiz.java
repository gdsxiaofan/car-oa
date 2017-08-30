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
    UserPermissionVo getUserPermisson(Integer userId);

    /**
     * 修改密码
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @return
     */
    Boolean updatePwd(Integer userId, String oldPwd, String newPwd);
    /**
     * 修改用户信息
     * @param employee
     * @return
     */
    void updateUser(Employee employee);
}
