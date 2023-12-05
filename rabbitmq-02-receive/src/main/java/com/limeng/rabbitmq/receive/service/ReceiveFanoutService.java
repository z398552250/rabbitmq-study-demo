package com.limeng.rabbitmq.receive.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiveFanoutService {

    @RabbitListener(queues = {"queue.fanout.a","queue.fanout.b"})
    public void receive(Message message){
        log.info("收到消息: {}",new String(message.getBody()));
    }

}
