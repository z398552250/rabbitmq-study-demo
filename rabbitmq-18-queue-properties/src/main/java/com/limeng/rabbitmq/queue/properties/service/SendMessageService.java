package com.limeng.rabbitmq.queue.properties.service;

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
        rabbitTemplate.convertAndSend("exchange.queue.properties","info",message);
        log.info("消息发送完成");
    }

    public void sendD(){
        for (int i = 0; i < 100; i++) {
            Message message = MessageBuilder.withBody("hello world".getBytes()).build();
            rabbitTemplate.convertAndSend("exchange.queue.properties","d",message);
        }
        log.info("消息发送完成");
    }

    public void sendE(){
        for (int i = 0; i < 100; i++) {
            Message message = MessageBuilder.withBody(("hello world"+i).getBytes()).build();
            rabbitTemplate.convertAndSend("exchange.queue.properties","e",message);
        }
        log.info("消息发送完成");
    }

}
