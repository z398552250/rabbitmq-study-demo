package com.limeng.rabbitmq.ttl.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendMessageService {



    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 设置单条消息的过期时间
     */
    public void send1(){
        MessageProperties messageProperties=new MessageProperties();
        messageProperties.setExpiration("15000");
        Message message = MessageBuilder.withBody("hello world".getBytes()).andProperties(messageProperties).build();
        rabbitTemplate.convertAndSend("exchange.ttl.one","info",message);
        log.info("单条消息设置过期时间发送完成");

    }

    public void send2(){
        Message message = MessageBuilder.withBody("hello world".getBytes()).build();
        rabbitTemplate.convertAndSend("exchange.ttl.queue","info",message);
        log.info("向已设定过期时间的队列发送消息完成");
    }


    /**
     * 将单条消息的过期时间设置为15s,然后向过期时间为30s的队列发送
     */
    public void send3(){
        MessageProperties messageProperties=new MessageProperties();
        messageProperties.setExpiration("35000");
        Message message = MessageBuilder.withBody("hello world".getBytes()).andProperties(messageProperties).build();
        rabbitTemplate.convertAndSend("exchange.ttl.queue","info",message);
        log.info("消息发送完成");
    }



}
