package com.xiaofan.car.persistence.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工单详细vo
 *
 * @author gongdaoshun
 * @date 2017/10/13
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TpmBillDetailVo {
    /****/
    private Integer id;

    /**检查信息id**/
    private Integer checkId;

    /**系统名称**/
    private String systemName;

    /**检查点**/
    private String checkPoint;

    /**检查/维护工作**/
    private String checkComment;

    /**设定值**/
    private String setValue;

    /**检查标记：0，待确认，1，正常，2，有故障，3，修复**/
    private Integer checkStatus;

    private String checkStatusName;

    public void setCheckStatus(int checkStatus){
        this.checkStatus = checkStatus;

        this.checkStatusName = checkStatusName;
    }
}
