package com.limeng.rabbitmq.exchange.properties.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 演示将消息发送至设置为internal的交换机上
     * 将会得到错误提示:
     * Shutdown Signal: channel error; protocol method: #method<channel.close>(reply-code=403, reply-text=ACCESS_REFUSED - cannot publish to internal exchange 'exchange.properties.fanout' in vhost 'powernode', class-id=60, method-id=40)
     */
    public void send1(){
        Message message = MessageBuilder.withBody("hello world".getBytes()).build();
        rabbitTemplate.convertAndSend("exchange.properties.fanout","",message);

        log.info("消息发送完成");

    }

    /**
     * 将消息发送至直连交换机上,并写错路由键,让交换机无法将消息路由至队列
     * 此时消息会从直接交换机转发至备用交换机,并由备用交换机路由至备用队列
     */
    public void send2(){
        Message message = MessageBuilder.withBody("hello world".getBytes()).build();
        rabbitTemplate.convertAndSend("exchange.properties.direct","info111",message);
        log.info("消息发送完成");


    }



}
