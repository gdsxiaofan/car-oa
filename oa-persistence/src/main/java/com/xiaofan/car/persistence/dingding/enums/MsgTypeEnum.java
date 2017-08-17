package com.xiaofan.car.persistence.dingding.enums;

/**
 * 定义钉钉消息的类型
 *
 * @author gongdaoshun
 * @date 2017/8/17
 * @since 1.0.0
 */
public enum MsgTypeEnum {
    TEXT("text","文本"),
    LINK("link","连接"),
    MARKDOWN("markdown","markdown"),
    ACTION_CARD("actionCard","actionCard"),
    FEED_CARD("feedCard","feedCard");

      private String code;
      private String name;

      MsgTypeEnum(String code,String name){
          this.code = code;
          this.name = name;
      }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
