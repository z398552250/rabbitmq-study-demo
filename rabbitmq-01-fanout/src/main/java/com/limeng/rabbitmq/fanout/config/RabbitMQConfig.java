package com.limeng.rabbitmq.fanout.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    //创建一个广播交换机,名称为exchange.fanout
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("exchange.fanout");
    }

    //创建一个队列,名称queue.fanout.a
    @Bean
    public Queue queueA(){
        return new Queue("queue.fanout.a");
    }

    //创建一个队列,名称queue.fanout.b
    @Bean
    public Queue queueB(){
        return new Queue("queue.fanout.b");
    }

    //将队列A与交换机exchange.fanout绑定
    @Bean
    public Binding bindingA(FanoutExchange fanoutExchange,Queue queueA){
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    //将队列B与交换机exchange.fanout绑定
    @Bean
    public Binding bindingB(FanoutExchange fanoutExchange,Queue queueB){
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }
}
