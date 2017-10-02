package com.xiaofan.car.persistence.enumType;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: bean
 * @Description:
 * @Date: Create in 2017/10/2-14:58
 */
public enum OrderType {
    look(0,"巡检单"),
    fix(1,"维修单"),
    verify(2,"待审核工单"),
    verified(3,"已审核"),
    all(4,"所有工单");
    private int value;
    private String desc;



    OrderType(int value, String desc) {
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
