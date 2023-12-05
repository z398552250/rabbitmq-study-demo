package com.limeng.rabbitmq.topic.service;

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
        String msg="这是一只猴子,消息将根据动物种类进行发送";
        Message message=new Message(msg.getBytes());
        rabbitTemplate.convertAndSend("exchange.topic","monkey.animal",message);
        msg="这是一个苹果";
        message=new Message(msg.getBytes());
        rabbitTemplate.convertAndSend("exchange.topic","apple.fruits.sweet",message);
        msg="这是一碗面条";
        message=new Message(msg.getBytes());
        rabbitTemplate.convertAndSend("exchange.topic","noodles.food.好吃",message);




        log.info("消息发送完成,时间:{}",new Date());
    }


}
