package com.limeng.rabbitmq.receive.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiveDirectService {

    @RabbitListener(queues = {"queue.direct.a","queue.direct.b"})
    public void receive(Message message){
        String msg=new String(message.getBody());
        log.info("队列名称: {} 收到消息:{}", message.getMessageProperties().getConsumerQueue(),msg);
    }

}
