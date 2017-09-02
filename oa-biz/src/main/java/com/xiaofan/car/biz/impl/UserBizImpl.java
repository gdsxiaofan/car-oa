package com.xiaofan.car.biz.impl;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.biz.UserBiz;
import com.xiaofan.car.dao.repository.EmployeeMapper;
import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.persistence.model.Memu;
import com.xiaofan.car.persistence.param.UserQueryParam;
import com.xiaofan.car.persistence.vo.MemuVo;
import com.xiaofan.car.persistence.vo.UserPermissionVo;
import com.xiaofan.car.service.MemuService;
import com.xiaofan.car.util.lang.MD5Util;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gongdaoshun on 2017/8/26.
 */
@Service
@Slf4j
public class UserBizImpl implements UserBiz {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private MemuService memuService;

    @Override
    public PageInfo<Employee> queryUserList(UserQueryParam userQueryParam) {
        return null;
    }

    @Override
    public UserPermissionVo getUserPermisson(Integer userId) {
        // 1.校验userId
        Assert.notNull(userId,"用户的id不能为空！");

        // 2.查询用户信息及角色信息
        UserPermissionVo userPermissionVo = employeeMapper.selectUserPermissionVo(userId);

        // 3.根据角色id获取对应的权限列表
        List<MemuVo> memuVoList= memuService.getMenuList(userPermissionVo.getRoleId());

        userPermissionVo.setMemuVoList(memuVoList);

        return userPermissionVo;
    }

    /**
     * 修改密码
     *
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @Override
    @Transactional
    public Boolean updatePwd(Integer userId, String oldPwd, String newPwd) {
        //1.判断原密码是否正确
        Employee employee= employeeMapper.selectEmployeeForUpdatePwd(userId, MD5Util.encode(oldPwd));
        if(employee==null){
            return false;
        }else {
            Employee update=new Employee();
            update.setId(userId);
            update.setEmployeePassword(MD5Util.encode(newPwd));
            employeeMapper.updateByPrimaryKeySelective(update);
            return true;
        }

    }

    /**
     * 修改用户信息
     *
     * @param employee
     * @return
     */
    @Override
    @Transactional
    public void updateUser(Employee employee) {
         employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public void delUser(Integer id) {
        Employee employee= new Employee();
        employee.setId(id);
        employee.setDeleteStatus(0);
        employeeMapper.updateByPrimaryKeySelective(employee);
    }
    /**
     * 是否启用用户
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public void isActiveUser(Integer id,Boolean isActive) {
        Employee employee= new Employee();
        employee.setId(id);
        employee.setIsActive(isActive);
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 新增用户信息
     *
     * @param employee
     * @return
     */
    @Override
    @Transactional
    public void addUser(Employee employee) {
        employeeMapper.insertSelective(employee);
    }
}
