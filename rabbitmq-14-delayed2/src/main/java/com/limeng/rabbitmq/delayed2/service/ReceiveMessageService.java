package com.limeng.rabbitmq.delayed2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiveMessageService {

    @RabbitListener(queues = "queue.delayed2")
    public void receive(Message message){
        log.info("接收消息:{}",new String(message.getBody()));
    }


}
