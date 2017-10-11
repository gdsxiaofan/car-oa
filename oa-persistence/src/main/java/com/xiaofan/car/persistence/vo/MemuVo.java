package com.xiaofan.car.persistence.vo;

import lombok.Data;

/**
 * 返回的菜单描述
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
@Data
public class MemuVo {
    /****/
    private Integer id;

    /**菜单名称**/
    private String name;

    /**菜单url**/
    private String href;

    /**待定**/
    private String icon;

    /**菜单层级：1最高**/
    private Integer orderTop;

    /**父菜单id**/
    private Integer parentId;

    /****/
    private Integer deleteStatus;
}

