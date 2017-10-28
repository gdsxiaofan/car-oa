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
public class CheckInfoParam {
    /****/
    private Integer id;

    /**设备id**/
    private Integer deviceId;

    /**系统id**/
    private Integer systemId;

    /**系统名称**/
    private String systemName;

    /**检查点**/
    private String checkPoint;

    /**检查/维护内容**/
    private String checkComment;

    /**设定值**/
    private String setValue;

    /**班次：1，早班；2：中班**/
    private Integer shiftsNo;

    /**巡检周期**/
    private Integer checkCycle;

    /**检查时间，格式为：hh:ss**/
    private String checkTime;

    /**设备名称**/
    private String deviceName;

    /**服务名称**/
    private String serviceName;

    /**任务描述**/
    private String serviceDescribe;

    /**首次巡检日期**/
    private String firstCheckTime;//前端传值只需精确到年月日即可

    /**最后一次巡检的日期**/
    private String lastCheckTime;//前端传值只需精确到年月日即可
}
