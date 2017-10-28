package com.xiaofan.car.persistence.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.xiaofan.car.persistence.enumType.ShiftsNoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 设备检查项的信息Vo
 *
 * @author gongdaoshun
 * @date 2017/10/13
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckInfoVo {
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

    private String shiftsNoName;

    //################################################### 原型改版后新增字段 ############################
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
    @JSONField(format="yyyy-MM-dd")
    private Date firstCheckTime;

    /**最后一次巡检的日期**/
    @JSONField(format="yyyy-MM-dd")
    private Date lastCheckTime;

    public void setShiftsNo(int shiftsNo){
        this.shiftsNo = shiftsNo;
        this.shiftsNoName = ShiftsNoEnum.valuesOf(shiftsNo)==null?"":ShiftsNoEnum.valuesOf(shiftsNo).getName();
    }
}
