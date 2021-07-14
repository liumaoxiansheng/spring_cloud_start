package com.demo.controller;

import com.demo.constant.WebSocketConstant;
import com.demo.domain.ReceiveMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: SubController
 * @description: 消息订阅的控制器
 * @author: th_legend
 * @date: 2021/7/7
 **/
@Controller
public class SubController {

    private final Logger logger = LoggerFactory.getLogger(SubController.class);

    @Autowired
    private SimpMessagingTemplate messageTemplate;

    @MessageMapping("/subscribe")
    public void subscribe(ReceiveMessage rm) {
        for (int i = 1; i <= 20; i++) {
            //广播使用convertAndSend方法，第一个参数为目的地，和js中订阅的目的地要一致
            messageTemplate.convertAndSend("/topic/getResponse", rm.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @MessageMapping("/queue")
    public void queue(ReceiveMessage rm) {
        System.out.println("进入方法");
        for (int i = 1; i <= 20; i++) {
            /*广播使用convertAndSendToUser方法，第一个参数为用户id，此时js中的订阅地址为
            "/user/" + 用户Id + "/message",其中"/user"是固定的*/
            messageTemplate.convertAndSendToUser("zhangsan", "/message", rm.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 广播
     *
     * @param message
     * @return
     */
    @MessageMapping("/welcome")
    @SendTo(WebSocketConstant.TOPIC+"/greetings")
    public Map say(Map message) {
        logger.info(message.get("chatType").toString());
        Map res = new HashMap();
        res.put("topic", message.get("chatType").toString());
        return res;
    }

    /**
     * 请求接口推送消息-（广播）
     *
     * @param message
     */
    @MessageMapping("/sendMes")
    public void sentMes(Map message) {
        logger.info(message + "xiaixixi");
        this.messageTemplate.convertAndSend(WebSocketConstant.QUEUE+"/msg", message);
    }

    /**
     * 点对点通信
     *
     * @param message
     */
    @MessageMapping(value = "/point")
    @SendToUser(WebSocketConstant.QUEUE+"/point")
    public String point(Map message) {
        logger.info(message.get("test") + "******");
        return "dd";
    }

    /**
     * 点对点通信
     *
     * @param message
     */
    @MessageMapping(value = "/points")
    public void point1(Map message) {
        logger.info(message.get("name") + "******");
        //发送消息给指定用户, 最后接受消息的路径会变成 /user/admin/queue/points
        messageTemplate.convertAndSendToUser("admin", "/queue/points", message);
    }

}
