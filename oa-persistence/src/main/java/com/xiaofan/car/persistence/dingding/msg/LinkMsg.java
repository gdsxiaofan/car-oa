package com.xiaofan.car.persistence.dingding.msg;

import lombok.Data;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/17
 * @since 1.0.0
 */
@Data
public class LinkMsg {
    private String msgtype;//消息类型，此时固定为:link
    private String title;//消息标题
    private String text;//消息内容。如果太长只会部分展示
    private String messageUrl;//点击消息跳转的URL
    private String picUrl;//图片URL
}
