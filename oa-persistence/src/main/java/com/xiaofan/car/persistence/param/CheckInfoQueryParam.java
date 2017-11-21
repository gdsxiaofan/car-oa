package com.xiaofan.car.persistence.param;

import lombok.Data;

/**
 * 检查信息的参数
 *
 * @author gongdaoshun
 * @date 2017/10/13
 * @since 1.0.0
 */
@Data
public class CheckInfoQueryParam {


    /**设备id**/
    private Integer deviceId;

    private Integer pageNum;
    private Integer pageSize;


}
