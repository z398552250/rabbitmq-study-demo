package com.limeng.rabbitmq.confirm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class SendMessageService {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        //ConfirmCallback,表示的是消息发送至交换机的回调,无论消息是否发送成功,都会执行此接口.
        //参数解释:
        //  回调参数,可以理解为发送消息时指定的回调数据
        //  消息是否发送成功, true 是 false 失败
        //  消息发送失败原因
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("发送消息至交换机成功,消息业务id: {}",correlationData.getId());
                return;
            }
            log.error("发送消息至交换机失败,消息业务id: {},原因: {}", correlationData.getId(), cause);
        });
    }

    public void send() {
        Message message = MessageBuilder.withBody("hello world".getBytes()).build();
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("order_123456789");
        rabbitTemplate.convertAndSend("exchange.confirmm", "info", message, correlationData);
        log.info("发送消息完成");
    }

}
