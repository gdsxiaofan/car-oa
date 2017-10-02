package com.xiaofan.car.persistence.param;

import com.xiaofan.car.persistence.enumType.OrderStatus;
import com.xiaofan.car.persistence.enumType.OrderType;
import lombok.Data;

/**
 * 工单列表
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
@Data
public class OrderListQueryParam {

    String orderNo;//工单号
    OrderStatus orderStatus;//工单状态
    OrderType orderType;//工单类别
    String doPerson;//处理人
    int pageSize;
    int pageNum;

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
