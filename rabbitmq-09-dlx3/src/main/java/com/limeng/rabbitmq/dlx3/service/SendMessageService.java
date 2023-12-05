package com.limeng.rabbitmq.dlx3.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send(){
        Message message = MessageBuilder.withBody("hello world".getBytes()).build();
        rabbitTemplate.convertAndSend("exchange.dlx3.normal","info",message);
        log.info("消息发送完成");
    }

}
