package com.xiaofan.car.dao.repository;

import com.xiaofan.car.persistence.model.Role;
import com.xiaofan.car.persistence.vo.RoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 
 * RoleMapper数据库操作接口类
 * 
 **/
@Repository
public interface RoleMapper{

    public List<RoleVo> getRoleVoList(
            @Param("roleName")String roleName
    );
}