package com.limeng.rabbitmq.receive.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiveQueuePropertiesService {

    /**
     * 实验队列的自动删除属性
     * @param message
     */
    @RabbitListener(queues = "queue.properties.a")
    public void receive(Message message){

    }

    @RabbitListener(queues = "queue.properties.c")
    public void receiveC(Message message){

    }



}
