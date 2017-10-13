package com.xiaofan.car.persistence.vo;

import com.xiaofan.car.persistence.enumType.ShiftsNoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public void setShiftsNo(int shiftsNo){
        this.shiftsNo = shiftsNo;
        this.shiftsNoName = ShiftsNoEnum.valuesOf(shiftsNo)==null?"":ShiftsNoEnum.valuesOf(shiftsNo).getName();
    }
}
