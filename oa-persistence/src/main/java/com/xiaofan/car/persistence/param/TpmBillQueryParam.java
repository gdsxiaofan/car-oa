package com.xiaofan.car.persistence.param;

import lombok.Data;

/**
 * 工单查询参数
 *
 * @author gongdaoshun
 * @date 2017/10/13
 * @since 1.0.0
 */
@Data
public class TpmBillQueryParam {
    Integer pageSize;
    Integer pageNum;
    String tpmBillName;
    /**工单状态：1，待处理，2：待审核，3：故障维修，4：修复完成，5处理完成**/
    Integer tpmStatus;

    /**工单类型：1：巡检工单，2：维修工单**/
    Integer tpmType;

    /**班次：1，早班；2，中班**/
    int shiftsNo;

    public void setPageSize(Integer pageSize){
        if(pageSize==null){
            pageSize=10;
        }
        this.pageSize=pageSize;
    }

    public void setPageNum(Integer pageNum){
        if(pageNum==null){
            pageNum=10;
        }
        this.pageNum=pageNum;
    }
}
