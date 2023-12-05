package com.limeng.rabbitmq.dlx3.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    //创建一个正常的交换机
    @Bean
    public DirectExchange directExchangeNormal(){
        return ExchangeBuilder.directExchange("exchange.dlx3.normal").build();
    }

    //创建一个正常的队列
    @Bean
    public Queue queueNormal(){
        Map<String,Object> arguments=new HashMap<>();
        //设置失效消息的转发至对应的死信交换机
        arguments.put("x-dead-letter-exchange","exchange.dlx3.dlx");
        //设置死信交换机的路由键
        arguments.put("x-dead-letter-routing-key","error");
        return QueueBuilder.durable("queue.dlx3.normal").withArguments(arguments).build();
    }

    //将正常的队列与正常的交换机绑定
    @Bean
    public Binding bindingNormal(DirectExchange directExchangeNormal,Queue queueNormal){
        return BindingBuilder.bind(queueNormal).to(directExchangeNormal).with("info");
    }

    //创建一个死信交换机
    @Bean
    public DirectExchange directExchangeDlx(){
        return ExchangeBuilder.directExchange("exchange.dlx3.dlx").build();
    }

    //创建一个死信队列
    @Bean
    public Queue queueDlx(){
        return QueueBuilder.durable("queue.dlx3.dlx").build();
    }

    //将死信队列与死信交换机进行绑定
    @Bean
    public Binding bindingDlx(DirectExchange directExchangeDlx,Queue queueDlx){
        return BindingBuilder.bind(queueDlx).to(directExchangeDlx).with("error");
    }
}
