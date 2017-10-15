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
}
