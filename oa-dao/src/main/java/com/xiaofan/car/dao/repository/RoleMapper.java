package com.xiaofan.car.dao.repository;

import com.xiaofan.car.persistence.model.Role;
import com.xiaofan.car.persistence.param.RoleParam;
import com.xiaofan.car.persistence.vo.RoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * RoleMapper数据库操作接口类
 **/
@Repository
public interface RoleMapper {

    List<RoleVo> getRoleVoList(
            @Param("roleName") String roleName
    );

    int deleteByPrimaryKey(@Param("id") Integer id);

    int deletePermissionByRoleId(Integer id);

    void insertPermissionWithRoleId(RoleParam roleParam);

    void updateByPrimaryKeySelective(Role role);

    void insertSelective(Role role);
}