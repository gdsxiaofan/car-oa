package com.xiaofan.car.service;

import com.xiaofan.car.persistence.model.Memu;
import com.xiaofan.car.persistence.param.RoleParam;
import com.xiaofan.car.persistence.vo.MemuVo;

import java.util.List;

/**
 * 菜单相关操作
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
public interface MemuService {
    List<MemuVo> getMenuList(Integer roleId);

    /**
     * 获取所有菜单
     * @return
     */
    List<Memu> getAllMenu();
    /**
     * 获取所有菜单
     * @param id   roleId
     * @return
     */
    RoleParam getAllMenuByRoleId(Integer id);

}
