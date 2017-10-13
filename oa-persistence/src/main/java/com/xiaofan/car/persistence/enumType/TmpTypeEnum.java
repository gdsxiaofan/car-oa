package com.xiaofan.car.persistence.enumType;

import lombok.Getter;

/**
 * 工单类型
 *
 * @author gongdaoshun
 * @date 2017/10/13
 * @since 1.0.0
 */
public enum TmpTypeEnum {
    ROUTING_INSPECTION(1,"巡检工单","O"),
    REPAIR(2,"维修工单","M");

    @Getter
    private int code;
    @Getter
    private String name;
    @Getter
    private String tpmCode;

    TmpTypeEnum(int code,String name,String tpmCode){
        this.code=code;
        this.name=name;
        this.tpmCode=tpmCode;
    }

    public static TmpTypeEnum valuesOf(int code){
        for(TmpTypeEnum tte:TmpTypeEnum.values()){
            if(tte.getCode()==code){
                return tte;
            }
        }
        return null;
    }
}
