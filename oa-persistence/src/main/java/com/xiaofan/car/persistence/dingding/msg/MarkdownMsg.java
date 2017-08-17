package com.xiaofan.car.persistence.dingding.msg;

import com.xiaofan.car.persistence.dingding.params.DealTypeAt;
import com.xiaofan.car.persistence.dingding.params.Markdown;
import lombok.Data;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/17
 * @since 1.0.0
 */
@Data
public class MarkdownMsg {
    private String msgtype;//此消息类型为固定markdown
    private Markdown markdown;//展示消息
    private DealTypeAt at;//@内容
}
