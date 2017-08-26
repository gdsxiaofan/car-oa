package com.xiaofan.car.persistence.vo;

import lombok.Data;

import java.util.List;

/**
 * 用户权限相关信息
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
@Data
public class UserPermissionVo {
    private String userName;
    private String employeeNo;
    private Integer userId;
    private Integer roleId;
    private List<MemuVo> memuVoList;
}
