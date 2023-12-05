package com.limeng.rabbitmq.returns.service;

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
        //ReturnsCallback是交换机路由消息至队列的回调函数,这个接口与ConfirmCallback不同,因为此接口只在交换机发送消息至队列失败时才会调用,如果发送正常,则不会执行此接口
        //参数说明:
        //只有一个message参数,此参数封装了消息发送失败的原因
        rabbitTemplate.setReturnsCallback(message -> {
            log.error("消息从交换机发送到队列失败,失败原因:{}", message.getReplyText());
        });
    }

    public void send() {
        Message message = MessageBuilder.withBody("hello world".getBytes()).build();
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("order_123456789");
        rabbitTemplate.convertAndSend("exchange.confirm", "info0", message, correlationData);
        log.info("发送消息完成");
    }

}
