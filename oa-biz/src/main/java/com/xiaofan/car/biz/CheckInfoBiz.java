package com.xiaofan.car.biz;

import com.github.pagehelper.PageInfo;
import com.xiaofan.car.persistence.model.CheckInfo;
import com.xiaofan.car.persistence.param.CheckInfoParam;
import com.xiaofan.car.persistence.param.CheckInfoQueryParam;
import com.xiaofan.car.persistence.vo.CheckInfoVo;

import java.util.List;

/**
 * 检查项信息biz业务层
 *
 * @author gongdaoshun
 * @date 2017/10/16
 * @since 1.0.0
 */
public interface CheckInfoBiz {

    /**
     * 获取检查项列表数据
     * @param checkInfoQueryParam
     * @return
     */
    public PageInfo<CheckInfoVo> getCheckInfoList(CheckInfoQueryParam checkInfoQueryParam);

    /**
     * 保存对应的检查项信息
     * @param checkInfoParam
     */
    public void addCheckInfo(CheckInfoParam checkInfoParam);

    /**
     * 更新对应的检查项的信息
     * @param checkInfoParam
     */
    public void updateCheckInfo(CheckInfoParam checkInfoParam);

    /**
     * 删除对应的检查项信息
     * @param checkId
     */
    public void deleteCheckInfo(Integer checkId);

}
