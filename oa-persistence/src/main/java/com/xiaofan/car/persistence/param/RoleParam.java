package com.xiaofan.car.persistence.param;

import lombok.Data;

import java.util.List;

/**
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
@Data
public class RoleParam {
    private Integer id;
    private String roleName;
    private List<Integer> checkMenu;
}
