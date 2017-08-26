package com.xiaofan.car.persistence.vo;

import lombok.Data;

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
    public JsonResult(Integer code,String message,T data){
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }
    public JsonResult(Integer code,String message){
        this.setCode(code);
        this.setMessage(message);
    }
}
