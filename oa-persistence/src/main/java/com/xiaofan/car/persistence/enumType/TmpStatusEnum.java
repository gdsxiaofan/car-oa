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
    PENDING(1,"待修复"),
    AUDITING(2,"待审核"),
    BREAKDOWN(3,"故障维修"),
    REPAIRED(4,"修复完成"),
    FINISHED(5,"处理完成");

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
