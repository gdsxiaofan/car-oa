package com.xiaofan.car.dao.repository;

import com.xiaofan.car.persistence.model.Employee;
import com.xiaofan.car.persistence.param.UserQueryParam;
import com.xiaofan.car.persistence.vo.EmployeeVo;
import com.xiaofan.car.persistence.vo.UserPermissionVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * EmployeeMapper数据库操作接口类
 **/
@Repository
public interface EmployeeMapper {

    /**
     * 插入员工信息
     *
     * @param employee
     * @return
     */
    Integer insertSelective(
            Employee employee
    );

    Employee selectEmployeeForLogin(
            @Param("employeeNo") String employeeNo,
            @Param("password") String password
    );


    UserPermissionVo selectUserPermissionVo(
            @Param("userId") Integer userId
    );

    /**
     * 列表查询对应得员工信息
     *
     * @param userQueryParam
     * @return
     */
    List<EmployeeVo> selectEmployeeVoByUserParam(UserQueryParam userQueryParam
    );

    Employee selectEmployeeForUpdatePwd(@Param("id") Integer id, @Param("oldPwd") String oldPwd);


    void updateByPrimaryKeySelective(Employee employee);
}