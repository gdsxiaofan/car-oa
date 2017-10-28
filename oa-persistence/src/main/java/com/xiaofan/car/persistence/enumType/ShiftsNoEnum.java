package com.xiaofan.car.persistence.enumType;

import lombok.Getter;

/**
 * 班次定义枚举类
 *
 * @author gongdaoshun
 * @date 2017/10/13
 * @since 1.0.0
 */
public enum ShiftsNoEnum {
    NO_SHIFTSNO(0,"无排班"),
    MORNING(1,"早班"),
    NOONING(2,"午班");

    @Getter
    private int code;

    @Getter
    private String name;

    ShiftsNoEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

    public static ShiftsNoEnum valuesOf(int code){
        for(ShiftsNoEnum sne:ShiftsNoEnum.values()){
            if(sne.getCode()==code){
                return sne;
            }
        }
        return null;
    }
}
