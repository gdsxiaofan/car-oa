package com.xiaofan.car.service;

import com.xiaofan.car.persistence.vo.MemuVo;

import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
public interface MemuService {
    public List<MemuVo> getMenuList(Integer roleId);
}
