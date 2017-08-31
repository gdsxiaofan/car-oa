package com.xiaofan.car.service.impl;

import com.xiaofan.car.dao.repository.MemuMapper;
import com.xiaofan.car.persistence.model.Memu;
import com.xiaofan.car.persistence.param.RoleParam;
import com.xiaofan.car.persistence.vo.MemuVo;
import com.xiaofan.car.service.MemuService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单控制service
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
@Slf4j
@Service
public class MemuServiceImpl implements MemuService {

    @Autowired
    private MemuMapper memuMapper;

    @Autowired
    DozerBeanMapper dozerBeanMapper;

    /**
     * 根据角色id获取对应的菜单选项
     * @param roleId
     * @return
     */
    @Override
    public List<MemuVo> getMenuList(Integer roleId) {
        // 1.校验参数
        Assert.notNull(roleId,"角色id不能为空！");

        // 2.根据角色id查询对应的权限菜单
        List<Memu> memuList =memuMapper.selectMemuByRoleId(roleId);
        if(CollectionUtils.isEmpty(memuList)){
            return null;
        }

        // 3.转换参数
        List<MemuVo> memuVoList = new ArrayList<>();
        for(Memu memu:memuList){
            MemuVo memuVo =dozerBeanMapper.map(memu,MemuVo.class);
            memuVoList.add(memuVo);
        }
        return memuVoList;
    }

    /**
     * 获取所有菜单
     *
     * @return
     */
    @Override
    public List<Memu> getAllMenu() {
        return memuMapper.getAllMenu();
    }

    /**
     * 获取所有菜单
     *
     * @param id roleId
     * @return
     */
    @Override
    public RoleParam getAllMenuByRoleId(Integer id) {
        RoleParam roleParam = new RoleParam();
        // 1.根据角色id查询对应的权限菜单
        List<Memu> memuList =memuMapper.selectMemuByRoleId(id);
        // 将角色id变成list
        List<Integer> list= memuList.stream().map(Memu::getId).collect(Collectors.toList());
        roleParam.setCheckMenu(list);
        return roleParam;
    }
}
