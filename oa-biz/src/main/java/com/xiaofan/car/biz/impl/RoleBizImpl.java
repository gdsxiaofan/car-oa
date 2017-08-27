package com.xiaofan.car.biz.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.RoleBiz;
import com.xiaofan.car.dao.repository.EmployeeMapper;
import com.xiaofan.car.dao.repository.RoleMapper;
import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.persistence.model.Role;
import com.xiaofan.car.persistence.param.RoleListQueryParam;
import com.xiaofan.car.persistence.param.UserQueryParam;
import com.xiaofan.car.persistence.vo.EmployeeVo;
import com.xiaofan.car.persistence.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
@Service
public class RoleBizImpl implements RoleBiz{

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public PageInfo<RoleVo> getRoleList(RoleListQueryParam roleListQueryParam) {

        // 1.设置分页
        PageHelper.startPage(roleListQueryParam.getPageNum(),roleListQueryParam.getPageSize());
        List<RoleVo> roleVoLit = roleMapper.getRoleVoList(roleListQueryParam.getRoleName());

        if(CollectionUtils.isEmpty(roleVoLit)){
            return null;
        }
        // 2.转换数据
        PageInfo<RoleVo> roleVoPageInfo = new PageInfo<>(roleVoLit);

        return roleVoPageInfo;
    }

    @Override
    public PageInfo<EmployeeVo> getEmployeeByRoleIdNoName(UserQueryParam userQueryParam) {
        //1.校验参数
        Assert.notNull(userQueryParam.getRoleId(),"角色id不能为空！");
        //2.查询用户里列表数据
        PageHelper.startPage(userQueryParam.getPageNum(),userQueryParam.getPageSize());
        List<EmployeeVo> employeeVoList = employeeMapper.selectEmployeeVoByUserParam(userQueryParam);

        return new PageInfo<EmployeeVo>(employeeVoList);
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
