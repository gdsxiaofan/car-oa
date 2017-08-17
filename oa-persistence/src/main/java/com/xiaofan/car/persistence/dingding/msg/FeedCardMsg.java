package com.xiaofan.car.persistence.dingding.msg;

import com.xiaofan.car.persistence.dingding.params.FeedCard;
import lombok.Data;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/17
 * @since 1.0.0
 */
@Data
public class FeedCardMsg {
    private String msgtype;//此消息类型为固定feedCard
    private FeedCard feedCard;//展示内容
}
