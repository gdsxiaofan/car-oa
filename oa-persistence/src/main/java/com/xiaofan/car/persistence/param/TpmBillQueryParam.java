package com.xiaofan.car.persistence.param;

/**
 * 工单查询参数
 *
 * @author gongdaoshun
 * @date 2017/10/13
 * @since 1.0.0
 */
public class TpmBillQueryParam {
    int pageSize;
    int pageNum;
    String tpmBillName;
    /**工单状态：1，待处理，2：待审核，3：故障维修，4：修复完成，5处理完成**/
    int tpmStatus;

    /**工单类型：1：巡检工单，2：维修工单**/
    int tpmType;

    /**班次：1，早班；2，中班**/
    int shiftsNo;
}
