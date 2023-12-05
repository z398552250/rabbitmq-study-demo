package com.limeng.rabbitmq.cluster.receive.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiveMessageService {

    @RabbitListener(queues = "queue.cluster")
    public void receive(Message message){
        log.info("收到消息:{}",new String(message.getBody()));
    }




}
