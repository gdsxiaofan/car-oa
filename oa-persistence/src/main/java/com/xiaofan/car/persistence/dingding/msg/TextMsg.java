package com.xiaofan.car.persistence.dingding.msg;

import com.xiaofan.car.persistence.dingding.params.DealTypeAt;
import com.xiaofan.car.persistence.dingding.params.MsgComment;
import lombok.Data;

/**
 * 文本消息模板
 *
 * @author gongdaoshun
 * @date 2017/8/17
 * @since 1.0.0
 */
@Data
public class TextMsg {
    String msgtype;//消息类型，此时固定为:text
    MsgComment text;//消息内容
    DealTypeAt at;//@选项
}
