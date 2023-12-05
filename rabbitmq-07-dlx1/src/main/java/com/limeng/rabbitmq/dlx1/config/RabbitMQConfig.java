package com.limeng.rabbitmq.dlx1.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {


    //创建一个正常的直连交换机
    @Bean
    public DirectExchange directExchangeNormal(){
        return ExchangeBuilder.directExchange("exchange.dlx1.normal").build();
    }

    //创建一个正常的队列
    @Bean
    public Queue queueNormal(){
        Map<String,Object> arguments=new HashMap<>();
        //设定队列的过期时间
        arguments.put("x-message-ttl",20000);
        //设定队列的消息到过期时间后转发到死信交换机
        arguments.put("x-dead-letter-exchange","exchange.dlx1.dlx");
        //设置转发到死信交换机的消息通过路由键发送到死信队列
        arguments.put("x-dead-letter-routing-key","error");
        return QueueBuilder.durable("queue.dlx1.normal").withArguments(arguments).build();
    }

    //将正常的队列与正常的交换机进行绑定
    @Bean
    public Binding bindingNormal(DirectExchange directExchangeNormal,Queue queueNormal){
        return BindingBuilder.bind(queueNormal).to(directExchangeNormal).with("info");
    }

    //创建一个死信交换机
    @Bean
    public DirectExchange directExchangeDlx(){
        return ExchangeBuilder.directExchange("exchange.dlx1.dlx").build();
    }

    //创建一个死信队列
    @Bean
    public Queue queueDlx(){
        return QueueBuilder.durable("queue.dlx1.dlx").build();
    }

    //将死信队列与死信交换机进行绑定
    @Bean
    public Binding bindingDlx(DirectExchange directExchangeDlx,Queue queueDlx){
        return BindingBuilder.bind(queueDlx).to(directExchangeDlx).with("error");
    }

}
