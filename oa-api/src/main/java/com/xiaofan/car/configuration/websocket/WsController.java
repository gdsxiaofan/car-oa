package com.xiaofan.car.configuration.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * @Author:duhongda
 * @Description:
 * @Date:Create in 14:57 2017/11/22
 */
@Controller
public class WsController {

    //用于转发数据(sendTo)
    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/welcome")
    public String say(String a) {
        System.out.println(a);
        template.convertAndSendToUser("1","/getResponse","asdadad");
        return "welcome," + a + " !";
    }
    @SendToUser("/getResponse")
    public String one2one(String msg){
        return msg;
    }

}
