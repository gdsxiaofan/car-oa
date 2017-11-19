package com.xiaofan.car.persistence.param;

import lombok.Data;

import java.util.List;

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
    Integer userId;//处理人员id
    Integer tmpType;//工单类型1:巡检工单 2：维修工单
    Integer dealType;//操作类型：2：巡检完成 3.待维修 4，维修完成 5，审核完成
    String attachmentIds;//附件id，用逗号隔开：1，2，3，4
    String desc;//备注
    String jwt;
}
