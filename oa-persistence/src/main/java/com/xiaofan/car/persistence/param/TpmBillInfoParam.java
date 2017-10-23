package com.xiaofan.car.persistence.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工单参数
 *
 * @author gongdaoshun
 * @date 2017/10/17
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TpmBillInfoParam {
    /****/
    private Integer id;

    /**工单名称**/
    private String tpmName;

    /**工单号**/
    private String tpmNo;

    /**设备id**/
    private Integer deviceId;

    /**设备名称**/
    private String deviceName;

    /**设备位置**/
    private String deviceLocation;

    /**设备区域**/
    private String deviceArea;

    /**审核驳回原因**/
    private String rejectReason;

    /**工单状态：1，待处理，2：待审核，3：故障维修，4：修复完成，5处理完成**/
    private Integer tpmStatus;

    /**工单类型：1：巡检工单，2：维修工单**/
    private Integer tpmType;

    /**班次：1，早班；2，中班**/
    private Integer shiftsNo;
}
