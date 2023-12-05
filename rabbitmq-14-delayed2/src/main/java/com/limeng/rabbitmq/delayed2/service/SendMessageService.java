package com.limeng.rabbitmq.delayed2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendMessageService {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void pay(){
        MessageProperties messageProperties=new MessageProperties();
        //设置消息延迟时间
        messageProperties.setHeader("x-delay",20000);
        Message message = MessageBuilder.withBody("pay".getBytes()).andProperties(messageProperties).build();
        rabbitTemplate.convertAndSend("exchange.delayed2","info",message);
        log.info("付款消息发送成功");
    }

    public void order(){
        MessageProperties messageProperties=new MessageProperties();
        //设置消息延迟时间
        messageProperties.setHeader("x-delay",10000);
        Message message = MessageBuilder.withBody("order".getBytes()).andProperties(messageProperties).build();
        rabbitTemplate.convertAndSend("exchange.delayed2","info",message);
        log.info("订单消息发送成功");
    }


}
