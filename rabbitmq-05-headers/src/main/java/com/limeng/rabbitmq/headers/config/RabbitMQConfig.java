package com.limeng.rabbitmq.headers.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    //创建一个头部交换机,名称为exchange.headers
    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange("exchange.headers");
    }

    //创建一个队列,名称为queue.headers.a
    @Bean
    public Queue queueA(){
        return QueueBuilder.durable("queue.headers.a").build();
    }

    //创建一个队列,名称queue.headers.b
    @Bean
    public Queue queueB(){
        return QueueBuilder.durable("queue.headers.b").build();
    }

    //将队列a与交换机进行绑定,并设定绑定参数
    @Bean
    public Binding bindingA(HeadersExchange headersExchange,Queue queueA){
        Map<String, Object> headerValues=new HashMap<>();
        headerValues.put("type","m");
        headerValues.put("status","1");
        return BindingBuilder.bind(queueA).to(headersExchange).whereAll(headerValues).match();
    }

    //将队列b与交换机进行绑定,并设定绑定参数
    @Bean
    public Binding bindingB(HeadersExchange headersExchange,Queue queueB){
        Map<String, Object> headerValues=new HashMap<>();
        headerValues.put("type","s");
        headerValues.put("status","0");
        return BindingBuilder.bind(queueB).to(headersExchange).whereAll(headerValues).match();
    }

}
