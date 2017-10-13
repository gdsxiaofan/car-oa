package com.xiaofan.car.persistence.vo;

import com.xiaofan.car.persistence.enumType.ShiftsNoEnum;
import com.xiaofan.car.persistence.enumType.TmpStatusEnum;
import com.xiaofan.car.persistence.enumType.TmpTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 工单的Vo
 *
 * @author gongdaoshun
 * @date 2017/10/13
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TpmBillVo {
    /****/
    private Integer id;

    /**工单名称**/
    private String tpmName;

    /****/
    private String tpmNo;

    /**设备id**/
    private Integer deviceId;

    /**设备名称**/
    private String deviceName;

    /**设备位置**/
    private String deviceLocation;

    /**设备区域**/
    private String deviceArea;

    /**工单完成时间**/
    private java.util.Date finishTime;

    /**工单审核时间**/
    private java.util.Date auditTime;

    /**审核驳回原因**/
    private String rejectReason;

    /**工单状态：1，待处理，2：待审核，3：故障维修，4：修复完成，5处理完成**/
    private Integer tpmStatus;

    private String tpmStatusName;

    /**工单类型：1：巡检工单，2：维修工单**/
    private Integer tpmType;

    private String tpmTypeName;

    /**班次：1，早班；2，中班**/
    private Integer shiftsNo;

    private String shiftsNoName;

    private List<TpmBillDetailVo> tpmBillDetailVoList;

    public void setTpmStatus(int tpmStatus){
        this.tpmStatus=tpmStatus;
        this.tpmStatusName = TmpStatusEnum.valuesOf(tpmStatus)==null?"":TmpStatusEnum.valuesOf(tpmStatus).getName();
    }

    public void setTpmType(int tpmType){
        this.tpmType=tpmType;
        this.tpmTypeName = TmpTypeEnum.valuesOf(tpmType)==null?"":TmpTypeEnum.valuesOf(tpmType).getName();
    }

    public void setShiftsNo(int shiftsNo){
        this.shiftsNo = shiftsNo;
        this.shiftsNoName = ShiftsNoEnum.valuesOf(shiftsNo)==null?"":ShiftsNoEnum.valuesOf(shiftsNo).getName();
    }
}
