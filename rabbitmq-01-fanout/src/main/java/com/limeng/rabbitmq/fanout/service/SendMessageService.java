package com.limeng.rabbitmq.fanout.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class SendMessageService {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send() {
        String msg = "hello world";
        Message message = new Message(msg.getBytes());
        rabbitTemplate.convertAndSend("exchange.fanout", "", message);
        log.info("消息发送完成,发送时间: {}", new Date());
    }


}
