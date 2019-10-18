package com.whsoft.springbootwebsocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wh
 * @date: 2019/10/17
 */
@RestController
public class WsController {

    private  final Logger logger = LoggerFactory.getLogger(WsController.class);

    @Autowired
    private SimpMessagingTemplate messageTemplate;

    /**
     * 广播
     * @param message
     * @return
     */
    @MessageMapping("/welcome")
    @SendTo("/topic/greetings")
    public Map say(Map message) {
        logger.info(message.get("chatType").toString());
        Map res = new HashMap();
        res.put("topic", message.get("chatType").toString());
        return res;
    }

    /**
     * 定时任务推送消息-（广播）
     * @throws Exception
     */
    // @Scheduled(fixedDelay=5000)
    public void priceAutoConvert() throws Exception {
        this.messageTemplate.convertAndSend("/topic/getResponse", "dddd");
    }

    /**
     * 请求接口推送消息-（广播）
     * @param message
     */
    @GetMapping("/sentMes")
    public void sentMes(String message) {
        logger.info(message + "xiaixixi");
        this.messageTemplate.convertAndSend("/queue/msg", message);
    }

    /**
     * 点对点通信
     * @param message
     */
    @MessageMapping(value = "/point")
    @SendToUser("/topic/point")
    public String point(Map message) {
        logger.info(message.get("test") + "******");
        return "dd";
    }

    /**
     * 点对点通信
     * @param message
     */
    @MessageMapping(value = "/points")
    public void point1(Map message) {
        logger.info(message.get("name") + "******" );
        //发送消息给指定用户, 最后接受消息的路径会变成 /user/admin/queue/points
        messageTemplate.convertAndSendToUser("admin", "/queue/points", message);
    }
}