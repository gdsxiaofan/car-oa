package com.xiaofan.car.persistence.param;

import lombok.Data;

/**
 * 工单的操作参数
 *
 * @author gongdaoshun
 * @date 2017/10/13
 * @since 1.0.0
 */
@Data
public class TpmBillParam {
    Integer id;//工单id
    Integer tmpType;//工单类型1:巡检工单 2：维修工单
    Integer dealType;//操作类型：1：确认完成/修复,2:发起维修申请，3：审核驳回，4：审核完成

}
