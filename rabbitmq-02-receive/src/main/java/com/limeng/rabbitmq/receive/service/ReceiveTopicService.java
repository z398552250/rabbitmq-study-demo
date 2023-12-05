package com.limeng.rabbitmq.receive.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiveTopicService {


    @RabbitListener(queues = "queue.topic.a")
    public void receiveQueueA(Message message) {
        log.info("这是水果和食物的消费者");
        log.info("接收到消息:{}",new String(message.getBody()));
    }

    @RabbitListener(queues = "queue.topic.b")
    public void receiveQueueB(Message message){
        log.info("这是动物的消费者");
        log.info("接收到消息:{}",new String(message.getBody()));
    }

}
