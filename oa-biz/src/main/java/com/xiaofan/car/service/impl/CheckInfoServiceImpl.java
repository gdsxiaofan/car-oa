package com.xiaofan.car.service.impl;

import com.xiaofan.car.dao.repository.CheckInfoMapper;
import com.xiaofan.car.persistence.model.CheckInfo;
import com.xiaofan.car.service.CheckInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gongdaoshun on 2017/10/28.
 */
@Service
@Slf4j
public class CheckInfoServiceImpl implements CheckInfoService {

    @Autowired
    CheckInfoMapper checkInfoMapper;

    @Override
    public List<CheckInfo> selectCheckInfoToday() {

        //1.处理当日开始时间，结束时间
        String startTime = null;
        String endTime = null;

        // 2.查询当日需要巡检的所有检查项
        List<CheckInfo> checkInfos = checkInfoMapper.selectTodayCheckInfo(null,null);
        return null;
    }
}
