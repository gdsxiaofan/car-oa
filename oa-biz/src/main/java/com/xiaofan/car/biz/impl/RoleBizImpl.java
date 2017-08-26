package com.xiaofan.car.biz.impl;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.RoleBiz;
import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.persistence.model.Role;
import com.xiaofan.car.persistence.param.RoleListQueryParam;
import com.xiaofan.car.persistence.vo.RoleVo;

import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
public class RoleBizImpl implements RoleBiz{
    @Override
    public PageInfo<RoleVo> getRoleList(RoleListQueryParam roleListQueryParam) {
        return null;
    }

    @Override
    public List<Employee> getEmployeeByRoleId(Integer roleId) {
        return null;
    }

    @Override
    public boolean updateRole(Role role) {
        return false;
    }

    @Override
    public boolean deleteRole(Integer roleId) {
        return false;
    }
}
