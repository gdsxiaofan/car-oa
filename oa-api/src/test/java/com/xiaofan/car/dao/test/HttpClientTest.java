package com.xiaofan.car.dao.test;


import com.xiaofan.car.persistence.dingding.msg.TextMsg;
import com.xiaofan.car.persistence.dingding.params.DealTypeAt;
import com.xiaofan.car.persistence.dingding.params.MsgComment;
import com.xiaofan.car.service.PushMsgService;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2017/8/17
 * @since 1.0.0
 */
@Slf4j
public class HttpClientTest extends BaseDaoTest{

    @Autowired
    private PushMsgService pushMsgService;

    @Test
    public void testDingDing() throws Exception{
        TextMsg textMsg = new TextMsg();
        MsgComment msgComment = new MsgComment();
        msgComment.setContent("有新的工单需要处理,点击链接开始处理工单：http://47.93.20.244:8011/swagger-ui.html");

        DealTypeAt dealTypeAt = new DealTypeAt();
        dealTypeAt.setIsAtAll("true");
//        List<String> mobileList = new ArrayList<>();
//        mobileList.add("13162510229");
//        mobileList.add("13162510253");
//        dealTypeAt.setAtMobiles(mobileList);
        textMsg.setText(msgComment);
        textMsg.setAt(dealTypeAt);

        boolean flag = pushMsgService.pushTextMsg(textMsg);
        }
}
