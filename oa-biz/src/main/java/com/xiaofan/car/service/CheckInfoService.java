package com.xiaofan.car.service;

import com.xiaofan.car.persistence.model.CheckInfo;

import java.util.List;

/**
 * Created by gongdaoshun on 2017/10/28.
 */
public interface CheckInfoService {

    /**
     * 查询当日需要巡检的检查任务
     * @return
     */
    public List<CheckInfo> selectCheckInfoToday();
}
