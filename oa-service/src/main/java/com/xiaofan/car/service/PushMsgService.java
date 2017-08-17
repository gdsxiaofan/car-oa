package com.xiaofan.car.service;

import com.xiaofan.car.persistence.dingding.msg.*;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/17
 * @since 1.0.0
 */
public interface PushMsgService {
    public boolean pushTextMsg(TextMsg textMsg);
    public boolean pushLinkMsg(LinkMsg textMsg);
    public boolean pushActionCardMsg(ActionCardMsg textMsg);
    public boolean pushFeedCardMsg(FeedCardMsg textMsg);
    public boolean pushMarkdownMsg(MarkdownMsg textMsg);
}
