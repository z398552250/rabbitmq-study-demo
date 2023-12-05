package com.limeng.rabbitmq.dlx1.service;

import com.limeng.rabbitmq.dlx1.config.RabbitMQConfig;
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
        rabbitTemplate.convertAndSend("exchange.dlx1.normal","info",message);
        log.info("发送消息完成");

    }

}
