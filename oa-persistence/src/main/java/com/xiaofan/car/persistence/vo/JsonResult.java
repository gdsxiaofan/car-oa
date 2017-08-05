package com.xiaofan.car.persistence.vo;

import lombok.Data;

import java.util.Calendar;

/**
 * Created by gongdaoshun on 2017/8/6.
 */
@Data
public class JsonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public JsonResult(){
        this.setCode(1);
        this.setMessage("success");
    }
}
