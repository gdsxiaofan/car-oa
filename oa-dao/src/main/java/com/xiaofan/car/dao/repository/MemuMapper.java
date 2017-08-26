package com.xiaofan.car.dao.repository;

import com.xiaofan.car.persistence.model.Memu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 
 * MemuMapper数据库操作接口类
 * 
 **/
@Repository
public interface MemuMapper{

    public List<Memu> selectMemuByRoleId(
            @Param("roleId")Integer roleId
    );

    public Integer insertSelective(
            Memu memu
    );

    public Integer updateByPrimaryKeySelective();
}