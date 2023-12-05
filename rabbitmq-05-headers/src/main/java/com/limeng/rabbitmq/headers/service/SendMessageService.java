package com.limeng.rabbitmq.headers.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SendMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", "m");
        headers.put("status", "1");
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeaders(headers);
        Message message = MessageBuilder.withBody("我的类型是m状态是1".getBytes()).andProperties(messageProperties).build();
        rabbitTemplate.convertAndSend("exchange.headers", "", message);

        headers.clear();
        headers.put("type","s");
        headers.put("status","0");
        messageProperties=new MessageProperties();
        messageProperties.setHeaders(headers);
        message = MessageBuilder.withBody("我是类型s状态是0".getBytes()).andProperties(messageProperties).build();
        rabbitTemplate.convertAndSend("exchange.headers","",message);

        log.info("发送消息完成");
    }


}
