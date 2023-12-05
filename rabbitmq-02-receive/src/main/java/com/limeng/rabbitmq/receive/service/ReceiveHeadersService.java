package com.limeng.rabbitmq.receive.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiveHeadersService {


    @RabbitListener(queues = {"queue.headers.a","queue.headers.b"})
    public void receive(Message message){
        log.info("队列名:{},接收到消息:{}",message.getMessageProperties().getConsumerQueue(),new String(message.getBody()));
    }

}
