package com.xiaofan.car.persistence.enumType;

import lombok.Getter;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;

/**
 * 工单详情中检查项的检查状态定义：检查标记：0，待确认，1，正常，2，有故障，3，修复
 *
 * @author gongdaoshun
 * @date 2017/10/13
 * @since 1.0.0
 */
public enum CheckStatusEnum {
    NO_CONFIRM(0,"待确认"),
    NORMAL(1,"正常"),
    TROUBLE(2,"有故障"),
    REPAIRED(3,"已修复");

    @Getter
    int code;
    @Getter
    String name;

    CheckStatusEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

    public static CheckStatusEnum valuesOf(int code){
        for(CheckStatusEnum cse :CheckStatusEnum.values()){
            if(cse.getCode()==code){
                return cse;
            }
        }
        return null;
    }


}
