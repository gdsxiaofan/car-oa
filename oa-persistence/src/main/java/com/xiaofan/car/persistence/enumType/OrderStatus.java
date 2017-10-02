package com.xiaofan.car.persistence.enumType;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: bean
 * @Description:
 * @Date: Create in 2017/10/2-14:58
 */
public enum OrderStatus {
    doing(0,"待完成"),
    done(1,"已完成"),
    all(2,"所有工单");
    private int value;
    private String desc;



    OrderStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }
    public String getDesc() {
        return desc;
    }


    @JsonValue
    public Map<String, Object> returnMap() {
        Map<String, Object> map = new HashMap();
        map.put("name", this.name());
        map.put("value", this.ordinal());
        map.put("desc", this.desc);
        return map;
    }
}
