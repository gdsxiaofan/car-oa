package com.xiaofan.car.persistence.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceParam {

    String roleName;
    String serviceName;
    int deviceId;
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
