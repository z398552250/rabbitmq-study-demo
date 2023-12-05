package com.limeng.rabbitmq.dlx4.config;

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
        return ExchangeBuilder.directExchange("exchange.dlx4.normal").build();
    }

    //创建一个正常的队列
    @Bean
    public Queue queueNormal(){
        Map<String,Object> arguments=new HashMap<>();
        //设置消息失效后转发至死信交换机
        arguments.put("x-dead-letter-exchange","exchange.dlx4.dlx");
        //设置死信交换的路由键
        arguments.put("x-dead-letter-routing-key","error");
        //设置队列的最大长度,示例:最大只有5条消息
        arguments.put("x-max-length",5);
        return QueueBuilder.durable("queue.dlx4.normal").withArguments(arguments).build();
    }

    //将正常的队列与正常的交换机进行绑定
    @Bean
    public Binding bindingNormal(DirectExchange directExchangeNormal,Queue queueNormal){
        return BindingBuilder.bind(queueNormal).to(directExchangeNormal).with("info");
    }

    //设置一个死信交换机
    @Bean
    public DirectExchange directExchangeDlx(){
        return ExchangeBuilder.directExchange("exchange.dlx4.dlx").build();
    }

    //设置一个死信队列
    @Bean
    public Queue queueDlx(){
        return QueueBuilder.durable("queue.dlx4.dlx").build();
    }

    //将死信队列与死信交换机进行绑定
    @Bean
    public Binding bindingDlx(DirectExchange directExchangeDlx,Queue queueDlx){
        return BindingBuilder.bind(queueDlx).to(directExchangeDlx).with("error");
    }

}
