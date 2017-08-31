package com.xiaofan.car.dao.repository;

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
}