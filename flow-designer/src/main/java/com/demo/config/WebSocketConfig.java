package com.demo.config;

import com.demo.constant.WebSocketConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @className: WebSocketConfig
 * @description: websocket config
 * @author: th_legend
 * @date: 2021/7/7
 **/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //topic用来广播，user用来实现p2p
        config.enableSimpleBroker(WebSocketConstant.TOPIC,WebSocketConstant.QUEUE,WebSocketConstant.USER);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册两个STOMP的endpoint，分别用于广播和点对点
        //registry.addEndpoint(WebSocketConstant.TOPIC_POINT).setAllowedOrigins("*").withSockJS();
        registry.addEndpoint(WebSocketConstant.QUEUE_POINT).setAllowedOrigins("*").withSockJS();
    }
}
