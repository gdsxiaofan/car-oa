package com.xiaofan.car.dao.repository;

import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.persistence.param.UserQueryParam;
import com.xiaofan.car.persistence.vo.UserPermissionVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 
 * EmployeeMapper数据库操作接口类
 * 
 **/
@Repository
public interface EmployeeMapper{

    /**
     * 插入员工信息
     * @param employee
     * @return
     */
    public Integer insertSelective(
            Employee employee
    );

    public Employee selectEmployeeForLogin(
            @Param("employeeNo") String employeeNo,
            @Param("password") String password
    );

    /**
     * 列表查询对应得员工信息
     * @param userQueryParam
     * @return
     */
    public Employee selectEmployeeForList(
            UserQueryParam userQueryParam
    );

    public UserPermissionVo selectUserPermissionVo(
            @Param("userId")Integer userId
    );
}