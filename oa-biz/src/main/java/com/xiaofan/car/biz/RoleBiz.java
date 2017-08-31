package com.xiaofan.car.biz;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.persistence.param.RoleListQueryParam;
import com.xiaofan.car.persistence.param.RoleParam;
import com.xiaofan.car.persistence.param.UserQueryParam;
import com.xiaofan.car.persistence.vo.EmployeeVo;
import com.xiaofan.car.persistence.vo.RoleVo;

/**
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
public interface RoleBiz {

    /**
     * 查询对应的权限列表数据
     * @param roleListQueryParam
     * @return
     */
    public PageInfo<RoleVo> getRoleList(RoleListQueryParam roleListQueryParam);

    /**
     * 根据权限id和工号和姓名查询该权限的所有用户信息
     * @param userQueryParam
     * @return
     */
    PageInfo<EmployeeVo> getEmployeeByRoleIdNoName(UserQueryParam userQueryParam);

    /**
     * 更新对应的权限信息
     * @param roleParam
     * @return
     */
    public void updateRole(RoleParam roleParam);

    /**
     * 删除对应的权限信息
     * @param roleId
     * @return
     */
    public void deleteRole(Integer roleId);
    /**
     * 新增角色
     * @param roleParam
     * @return
     */
    void insertRole(RoleParam roleParam);
}
