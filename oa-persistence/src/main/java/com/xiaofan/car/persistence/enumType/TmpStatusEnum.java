package com.xiaofan.car.persistence.enumType;

import lombok.Getter;

/**
 * 工单状态枚举
 *
 * @author gongdaoshun
 * @date 2017/10/13
 * @since 1.0.0
 */
public enum TmpStatusEnum {
    PENDING(1,"未巡检"),
    PENDED(2,"巡检通过"),
    REPAIRING(3,"待维修"),
    REPAIRED(4,"维修完成"),
    FINISHED(5,"已完成"),
    PEND_OVERDUE(6,"过期"),
    REPAIR_OVERDUE(7,"过期"),
    AUDIT_OVERDUE(8,"过期");

    @Getter
    private int code;
    @Getter
    private String name;

    TmpStatusEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

    public static TmpStatusEnum valuesOf(int code){
        for(TmpStatusEnum tse:TmpStatusEnum.values()){
            if(tse.getCode()==code){
                return tse;
            }
        }
        return null;
    }

}
