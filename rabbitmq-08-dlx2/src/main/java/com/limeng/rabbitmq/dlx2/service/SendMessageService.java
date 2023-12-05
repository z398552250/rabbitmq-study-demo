package com.limeng.rabbitmq.dlx2.service;

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

    public void send(){
        MessageProperties messageProperties=new MessageProperties();
        messageProperties.setExpiration("20000");
        Message message = MessageBuilder.withBody("hello world".getBytes()).andProperties(messageProperties).build();
        rabbitTemplate.convertAndSend("exchange.dlx2.normal","info",message);
        log.info("发送消息完成");
    }

}
