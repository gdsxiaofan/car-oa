package com.xiaofan.car.persistence.enumType;

import lombok.Getter;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/10/23
 * @since 1.0.0
 */
public enum IsEffectiveEnum {
    EFFECTIVE(1,"有效"),
    NO_EFFECTIVE(2,"无效");

    @Getter
    int code;

    @Getter
    String name;

    IsEffectiveEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

    public static IsEffectiveEnum valuesOf(int code){
        for(IsEffectiveEnum ie:IsEffectiveEnum.values()){
            if(ie.getCode()==code){
                return ie;
            }
        }
        return null;
    }
}
