package com.xiaofan.car.dao.test;

import com.xiaofan.car.persistence.dingding.msg.*;
import com.xiaofan.car.persistence.dingding.params.*;
import com.xiaofan.car.service.PushMsgService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gongdaoshun on 2017/8/18.
 */
public class PushMsgServiceTest extends BaseDaoTest {

    @Autowired
    private PushMsgService pushMsgService;

    @Test
    public void pushTextMsgTest() {
        TextMsg textMsg = new TextMsg();
        MsgComment msgComment = new MsgComment();
        msgComment.setContent("有新的工单需要处理,点击链接开始处理工单：http://47.93.20.244:8011/swagger-ui.html");

        DealTypeAt dealTypeAt = new DealTypeAt();
        dealTypeAt.setIsAtAll(true);
//        List<String> mobileList = new ArrayList<>();
//        mobileList.add("13162510229");
//        mobileList.add("13162510253");
//        dealTypeAt.setAtMobiles(mobileList);
        textMsg.setText(msgComment);
        textMsg.setAt(dealTypeAt);

        pushMsgService.pushTextMsg(textMsg);
    }

    @Test
    public void pushLinkMsgTest() {
        LinkMsg linkMsg = new LinkMsg();
        LinksCommentForLinks linksComment = new LinksCommentForLinks();
        linksComment.setText("这个即将发布的新版本，创始人陈航（花名“无招”）称它为“红树林”。\n" +
                "而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林”？\"");
        linksComment.setTitle("时代的火车向前开");
        linksComment.setMessageUrl("https://mp.weixin.qq.com/s?__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI");

        linkMsg.setLink(linksComment);

        pushMsgService.pushLinkMsg(linkMsg);
    }

    @Test
    public void pushActionCardMsgTest() {
        ActionCardMsg actionCardMsg = new ActionCardMsg();

        ActionCard actionCard = new ActionCard();
        actionCard.setTitle("乔布斯 20 年前想打造一间苹果咖啡厅，而它正是 Apple Store 的前身");
        actionCard.setText("![screenshot](@lADOpwk3K80C0M0FoA)### 乔布斯 20 年前想打造的苹果咖啡厅Apple Store 的设计正从原来满满的科技感走向生活化，而其生活化的走向其实可以追溯到 20 年前苹果一个建立咖啡馆的计划");
        actionCard.setBtnOrientation("0");
        actionCard.setHideAvatar("0");

        List<ActionComment> actionComments = new ArrayList<>();
        ActionComment actionComment1 = new ActionComment();
        actionComment1.setTitle("内容不错");
        actionComment1.setActionURL("https://www.dingtalk.com/");

        ActionComment actionComment2 = new ActionComment();
        actionComment2.setTitle("不感兴趣");
        actionComment2.setActionURL("https://www.dingtalk.com/");

        actionComments.add(actionComment1);
        actionComments.add(actionComment2);

        actionCard.setBtns(actionComments);

        actionCardMsg.setActionCard(actionCard);

        pushMsgService.pushActionCardMsg(actionCardMsg);
    }

    @Test
    public void pushFeedCardMsgTest() {
        FeedCardMsg feedCardMsg = new FeedCardMsg();

        List<LinksCommentForFeedCard> links = new ArrayList<>();
        LinksCommentForFeedCard link1 = new LinksCommentForFeedCard();
        link1.setTitle("时代的火车向前开");
        link1.setMessageURL("https://mp.weixin.qq.com/s?__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI");
        link1.setPicURL("https://www.dingtalk.com/");

        LinksCommentForFeedCard link2 = new LinksCommentForFeedCard();
        link2.setTitle("时代的火车向前开2");
        link2.setMessageURL("https://mp.weixin.qq.com/s?__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI");
        link2.setPicURL("https://www.dingtalk.com/");

        links.add(link1);
        links.add(link2);
        FeedCard feedCard = new FeedCard();
        feedCard.setLinks(links);
        feedCardMsg.setFeedCard(feedCard);

        pushMsgService.pushFeedCardMsg(feedCardMsg);
    }

    @Test
    public void pushMarkdownMsgTest() {
        MarkdownMsg markdownMsg = new MarkdownMsg();

        Markdown markdown = new Markdown();
        markdown.setTitle("杭州天气");
        markdown.setText("#### 杭州天气 @156xxxx8827\n" +
                "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
                "> ![screenshot](http://image.jpg)\n"  +
                "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
        markdownMsg.setMarkdown(markdown);

        DealTypeAt dealTypeAt = new DealTypeAt();
        dealTypeAt.setIsAtAll(false);

        markdownMsg.setAt(dealTypeAt);

        pushMsgService.pushMarkdownMsg(markdownMsg);
    }
}
