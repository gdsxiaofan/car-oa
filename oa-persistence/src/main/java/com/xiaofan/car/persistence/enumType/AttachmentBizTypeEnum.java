package com.xiaofan.car.persistence.enumType;

import lombok.Getter;

/**
 * 附件类型定义
 *
 * @author gongdaoshun
 * @date 2017/10/16
 * @since 1.0.0
 */
public enum AttachmentBizTypeEnum {
    TPM_TYPE(1,"工单"),
    TPM_DETAIL_TYPE(2,"工单详情"),
    DEVICE_TYPE(3,"设备类型"),
    CHECK_TYPE(4,"检查项类型");

    @Getter
    int code;
    @Getter
    String name;

    AttachmentBizTypeEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

    public static AttachmentBizTypeEnum valuesOf(Integer code){
        for(AttachmentBizTypeEnum ab:AttachmentBizTypeEnum.values()){
            if(ab.getCode()==code){
                return ab;
            }
        }
        return  null;
    }
}
