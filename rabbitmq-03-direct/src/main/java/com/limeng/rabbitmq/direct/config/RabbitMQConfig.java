package com.limeng.rabbitmq.direct.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //创建直连交换机,名称为 exchange.direct
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("exchange.direct");
    }

    //创建队列A,名称为queue.direct.a
    @Bean
    public Queue queueA(){
        return new Queue("queue.direct.a");
    }

    //创建队列B,名称为queue.direct.b
    @Bean
    public Queue queueB(){
        return new Queue("queue.direct.b");
    }

    //将队列A与交换机exchange.direct进行绑定,路由键设置为error
    @Bean
    public Binding bindingA(DirectExchange directExchange,Queue queueA){
        return BindingBuilder.bind(queueA).to(directExchange).with("error");
    }

    //将队列B与交换机exchange.direct进行绑定,路由键设置为info
    @Bean
    public Binding bindingB1(DirectExchange directExchange,Queue queueB){
        return BindingBuilder.bind(queueB).to(directExchange).with("info");
    }

    //将队列B与交换机exchange.direct进行绑定,路由键设置为error
    @Bean
    public Binding bindingB2(DirectExchange directExchange,Queue queueB){
        return BindingBuilder.bind(queueB).to(directExchange).with("error");
    }

    //将队列B与交换机exchange.direct进行绑定,路由键设置为warning
    @Bean
    public Binding bindingB3(DirectExchange directExchange,Queue queueB){
        return BindingBuilder.bind(queueB).to(directExchange).with("warning");
    }







}
