package com.xiaofan.car.dao.repository;

import com.xiaofan.car.persistence.model.Employee;
import org.apache.ibatis.annotations.Param;


/**
 * 
 * EmployeeMapper数据库操作接口类
 * 
 **/

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

}