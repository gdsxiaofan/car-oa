package com.xiaofan.car.biz;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.persistence.model.Role;
import com.xiaofan.car.persistence.param.RoleListQueryParam;
import com.xiaofan.car.persistence.vo.EmployeeVo;
import com.xiaofan.car.persistence.vo.RoleVo;
import lombok.Data;

import javax.management.relation.RoleList;
import java.util.List;

/**
 * TODO: 请添加描述
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
     * 根据权限id查询该权限的所有用户信息
     * @param roleId
     * @return
     */
    public List<EmployeeVo> getEmployeeByRoleId(Integer roleId);

    /**
     * 更新对应的权限信息
     * @param role
     * @return
     */
    public boolean updateRole(Role role);

    /**
     * 删除对应的权限信息
     * @param roleId
     * @return
     */
    public boolean deleteRole(Integer roleId);
}
