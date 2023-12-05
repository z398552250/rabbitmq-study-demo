package com.limeng.rabbitmq.ttl.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig1 {

    //创建一个直连交换机,名称为exchange.ttl.one,做单条消息的过期时间实验
    @Bean
    public DirectExchange directExchangeA(){
        return ExchangeBuilder.directExchange("exchange.ttl.one").build();
    }

    //创建一个队列,名称为queue.ttl.one
    @Bean
    public Queue queueA(){
        return QueueBuilder.durable("queue.ttl.one").build();
    }

    //将队列与交换进行绑定,路由key设置为info
    @Bean
    public Binding bindingA(DirectExchange directExchangeA,Queue queueA){
        return BindingBuilder.bind(queueA).to(directExchangeA).with("info");
    }
}
