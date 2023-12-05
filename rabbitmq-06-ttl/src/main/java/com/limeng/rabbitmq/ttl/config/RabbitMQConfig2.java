package com.limeng.rabbitmq.ttl.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig2 {

    //创建一个交换机,名称为exchange.ttl.queue,做队列的过期时间实验
    @Bean
    public DirectExchange directExchangeB(){
        return ExchangeBuilder.directExchange("exchange.ttl.queue").build();
    }

    //创建一个队列,设置过期时间参数,名称为queue.ttl.queue
    @Bean
    public Queue queueB(){
        Map<String,Object> arguments=new HashMap<>();
        arguments.put("x-message-ttl",30000);
        return QueueBuilder.durable("queue.ttl.queue").withArguments(arguments).build();
    }

    //将队列与交换机进行绑定,路由键为info
    @Bean
    public Binding bindingB(DirectExchange directExchangeB,Queue queueB){
        return BindingBuilder.bind(queueB).to(directExchangeB).with("info");
    }



}
