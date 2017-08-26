package com.xiaofan.car.persistence.param;

import lombok.Data;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
@Data
public class RoleListQueryParam {

    String roleName;
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
