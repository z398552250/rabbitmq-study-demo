package com.limeng.rabbitmq.delayed1.service;

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


    public void pay(){
        Message message = MessageBuilder.withBody("pay".getBytes()).build();
        rabbitTemplate.convertAndSend("exchange.delayed1","pay",message);
        log.info("付款消息发送完成");
    }

    public void order(){
        Message message = MessageBuilder.withBody("order".getBytes()).build();
        rabbitTemplate.convertAndSend("exchange.delayed1","order",message);
        log.info("订单消息发送完成");
    }




}
