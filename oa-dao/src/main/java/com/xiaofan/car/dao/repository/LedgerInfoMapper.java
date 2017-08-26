package com.xiaofan.car.dao.repository;

import com.xiaofan.car.persistence.model.LedgerInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * LedgerInfoMapper数据库操作接口类
 **/
@Mapper
@Component(value = "ledgerInfoMapper")
public interface LedgerInfoMapper {

    Integer insertSelective(LedgerInfo ledgerInfo);

    LedgerInfo selectLedgerInfoById(@Param(value = "id") Integer id);

    List<LedgerInfo> selectLedgerInfosByLedgerInfo(LedgerInfo ledgerInfo);
}