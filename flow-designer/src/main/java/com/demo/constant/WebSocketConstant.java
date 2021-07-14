package com.demo.constant;

/**
 * @className: WebSocketConstant
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/7
 **/
public class WebSocketConstant {
    private WebSocketConstant(){}

    // 广播模式
    public final static String TOPIC="/topic";

    // 点对点模式
    public final static String QUEUE="/queue";

    // 默认用户的模式
    public final static String USER="/user";

    // 广播终端
    public final static String TOPIC_POINT="topicServer";

    // 点对点终端
    public final static String QUEUE_POINT="queueServer";
}
