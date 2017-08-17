package com.xiaofan.car.persistence.dingding.msg;

import com.xiaofan.car.persistence.dingding.params.LinksCommentForLinks;
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
    private LinksCommentForLinks link;
}
