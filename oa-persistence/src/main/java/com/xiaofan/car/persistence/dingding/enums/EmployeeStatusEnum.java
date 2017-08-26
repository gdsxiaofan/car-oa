package com.xiaofan.car.persistence.dingding.enums;

import com.xiaofan.car.persistence.model.Employee;
import lombok.Getter;

/**
 * 员工状态枚举
 *
 * @author gongdaoshun
 * @date 2017/8/26
 * @since 1.0.0
 */
public enum EmployeeStatusEnum {

    ON_JOB(1,"在职"),
    VACATION(2,"休假"),
    OUT_JOB(3,"离职");

    @Getter
    Integer code;
    @Getter
    String name;

    EmployeeStatusEnum(Integer code,String name){
        this.code=code;
        this.name = name;
    }


    public static EmployeeStatusEnum valuesOf(Integer code){
        for(EmployeeStatusEnum employeeStatusEnum : EmployeeStatusEnum.values()){
            if(employeeStatusEnum.getCode().equals(code)){
                return employeeStatusEnum;
            }
        }
        return null;
    }
}
