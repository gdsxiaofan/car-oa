package com.xiaofan.car.persistence.param;

import com.xiaofan.car.persistence.enumType.OrderStatus;
import com.xiaofan.car.persistence.enumType.OrderType;
import com.xiaofan.car.persistence.model.Employee;
import lombok.Data;

/**
 * 工单列表
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
@Data
public class OrderParam {

    int id;//工单主键
    String orderNo;//工单号
    OrderStatus orderStatus;//工单状态
    OrderType orderType;//工单类别
    String doPerson;//处理人
    Employee employee;
}
