package com.xiaofan.car.persistence.param;

import lombok.Data;

/**
 * Created by gongdaoshun on 2017/8/26.
 */
@Data
public class UserQueryParam {
    private String  userName;
    private String employeeNo;
    private String userMobile;
    private Integer pageNum;
    private Integer pageSize;
}
