package com.xiaofan.car.persistence.dingding.params;

import lombok.Data;

/**
 * 按钮的信息：title-按钮方案，actionURL-点击按钮触发的URL
 *
 * @author gongdaoshun
 * @date 2017/8/17
 * @since 1.0.0
 */
@Data
public class ActionComment {
    private String title;
    private String actionURL;
}
