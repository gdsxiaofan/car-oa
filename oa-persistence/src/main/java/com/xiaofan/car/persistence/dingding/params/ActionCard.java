package com.xiaofan.car.persistence.dingding.params;

import lombok.Data;

import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/17
 * @since 1.0.0
 */
@Data
public class ActionCard {
    private String title;   //首屏会话透出的展示内容
    private String text;    //markdown格式的消息
    private String singleTitle;//单个按钮的方案。(设置此项和singleURL后btns无效。)
    private String singleURL;//点击singleTitle按钮触发的URL
    private String hideAvatar;  //0-正常发消息者头像,1-隐藏发消息者头像
    private String btnOrientation;//	0-按钮竖直排列，1-按钮横向排列
    private List<ActionComment> btns;//按钮的信息：title-按钮方案，actionURL-点击按钮触发的URL
}
