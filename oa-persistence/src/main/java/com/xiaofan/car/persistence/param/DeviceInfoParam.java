package com.xiaofan.car.persistence.param;

import lombok.Data;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/10/13
 * @since 1.0.0
 */
@Data
public class DeviceInfoParam {
    /****/
    private Integer id;

    /**巡检天数**/
    private Integer routingDays;

    /**设备名称**/
    private String deviceName;

    /**区域**/
    private String area;

    /**位置**/
    private String location;

    /**开始时间**/
    private String fromDate;

    /**结束时间**/
    private String toDate;

}
