package com.xiaofan.car.persistence.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 设备信息vo,用于设备管理展示设备的相关信息
 *
 * @author gongdaoshun
 * @date 2017/10/13
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInfoVo {
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

    /**开始时间 yyyy-MM-dd**/
    private String fromDate;

    /**结束时间 yyyyy-MM-dd**/
    private String toDate;

    private List<CheckInfoVo> checkInfoVoList;
}
