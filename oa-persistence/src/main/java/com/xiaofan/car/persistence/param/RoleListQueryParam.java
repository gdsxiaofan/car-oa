package com.xiaofan.car.persistence.param;

import lombok.Data;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
@Data
public class RoleListQueryParam {

    String roleName;
    int pageSize =10;
    int pageNum =1;
}
