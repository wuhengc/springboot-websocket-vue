package com.whsoft.springbootwebsocket.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author: wh
 * @date: 2019/10/17
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements  WebSocketMessageBrokerConfigurer {

    /**
     * 配置信息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 订阅Broker名称,接受消息用户必须以这个开头的路径才能收到消息
        config.enableSimpleBroker("/user", "/topic", "/queue");
        // 全局使用的消息前缀（客户端订阅路径上会体现出来），客户端主动发送消息会以这里配置的前缀访问 @MessageMapping 配置的路径
        config.setApplicationDestinationPrefixes("/app");
        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        config.setUserDestinationPrefix("/user");
    }

    /**
     * 注册stomp的端点
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 允许使用socketJs方式访问，endpointSang，允许跨域
        // 在网页上我们就可以通过这个链接
        // http://localhost:8080/endpointSang
        // 来和服务器的WebSocket连接
        registry.addEndpoint("/endpointSang")
               // .addInterceptors(new HttpSessionHandshakeInterceptor())
                .setAllowedOrigins("*") // 允许跨域设置
                .withSockJS();
    }

}