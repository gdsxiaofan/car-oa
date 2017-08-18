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
public class DealTypeAt {
    private List<String> atMobiles;//被@人的手机号
    private Boolean isAtAll;//@所有人时:true,否则为:false
}
