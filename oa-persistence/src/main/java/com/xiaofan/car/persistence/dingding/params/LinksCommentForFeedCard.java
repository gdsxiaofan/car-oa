package com.xiaofan.car.persistence.dingding.params;

import lombok.Data;

/**
 * Created by gongdaoshun on 2017/8/18.
 */
@Data
public class LinksCommentForFeedCard {
    private String title;//单条信息文本
    private String messageURL;//点击单条信息到跳转链接
    private String picURL;//单条信息后面图片的URL
}
