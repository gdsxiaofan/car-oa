package com.xiaofan.car.persistence.dingding.params;

import lombok.Data;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/17
 * @since 1.0.0
 */
@Data
public class LinksCommentForLinks {
    private String text;
    private String title;//单条信息文本
    private String messageUrl;//点击单条信息到跳转链接
    private String picUrl;//单条信息后面图片的URL
}
