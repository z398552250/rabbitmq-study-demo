package com.limeng.rabbitmq.direct.service;

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

    public void send(){
        Message message=new Message("info".getBytes());
        rabbitTemplate.convertAndSend("exchange.direct","info",message);
        log.info("发送消息完成,时间:{}",new Date());
    }


}
