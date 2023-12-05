package com.limeng.rabbitmq.dlx4.service;

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


    public void send() {
        for (int i = 1; i <= 8; i++) {
            Message message = MessageBuilder.withBody(("hello world"+i).getBytes()).build();
            rabbitTemplate.convertAndSend("exchange.dlx4.normal", "info", message);
        }


        log.info("消息发送完成");
    }


}
