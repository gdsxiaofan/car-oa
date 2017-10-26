package com.xiaofan.car.dao.repository;

import com.xiaofan.car.persistence.model.ArrangeInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 
 * ArrangeInfoMapper数据库操作接口类
 * 
 **/
@Repository
public interface ArrangeInfoMapper{

    public Integer insertSelective(ArrangeInfo arrangeInfo);

    public Integer updateByPrimaryKeySelective(ArrangeInfo arrangeInfo);

    public List<ArrangeInfo> selectArrangeInfoList();

    public Integer deleteArrangeInfo(
            @Param("id")Integer id
    );

    /**
     * 关闭当前id的其他所有启动的早晚班设置
     * @param id
     * @return
     */
    public boolean updateForPause(@Param("id")Integer id);
}