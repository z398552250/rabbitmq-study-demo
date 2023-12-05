package com.limeng.rabbitmq.delayed1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiveMessageService {

    @RabbitListener(queues = "queue.delayed1.dlx")
    public void pay(Message message){
        log.info("收到信息:{}",new String(message.getBody()));
    }

}
