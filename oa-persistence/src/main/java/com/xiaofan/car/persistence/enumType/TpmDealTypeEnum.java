package com.xiaofan.car.persistence.enumType;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/10/19
 * @since 1.0.0
 */
public enum TpmDealTypeEnum {
    //操作类型：1：确认完成/修复,2:发起维修申请，3：审核驳回，4：审核完成
    FINISHED(1,"确认完成/修复"),
    repair_apply(2,"维修申请"),
    audit_reject(3,"审核驳回"),
    audit_confirmed(4,"审核完成");


    int code;
    String name;

    TpmDealTypeEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

}
