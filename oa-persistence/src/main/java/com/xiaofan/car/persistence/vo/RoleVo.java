package com.xiaofan.car.persistence.vo;

import lombok.Data;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
@Data
public class RoleVo {
    /****/
    private Integer id;

    /**
     * 角色名称
     **/
    private String roleName;

    /**
     * 父节点权限id
     **/
    private Integer parentRoleId;

    /**
     * 角色code
     **/
    private String roleCode;

}
