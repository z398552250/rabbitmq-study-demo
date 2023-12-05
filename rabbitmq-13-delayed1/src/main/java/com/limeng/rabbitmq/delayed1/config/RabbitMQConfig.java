package com.limeng.rabbitmq.delayed1.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //创建一个直连交换机
    @Bean
    public DirectExchange directExchange(){
        return ExchangeBuilder.directExchange("exchange.delayed1").build();
    }

    //创建一个付款队列
    @Bean
    public Queue queueNormalPay(){
        return QueueBuilder.durable("queue.delayed1.normal.pay").ttl(10000).deadLetterExchange("exchange.delayed1").deadLetterRoutingKey("error").build();
    }

    //创建一个订单队列
    @Bean
    public Queue queueNormalOrder(){
        return QueueBuilder.durable("queue.delayed1.normal.order").ttl(20000).deadLetterExchange("exchange.delayed1").deadLetterRoutingKey("error").build();
    }

    //将付款队列与交换机进行绑定
    @Bean
    public Binding bindingNormalPay(DirectExchange directExchange,Queue queueNormalPay){
        return BindingBuilder.bind(queueNormalPay).to(directExchange).with("pay");
    }

    //将订单队列与交换机进行绑定
    @Bean
    public Binding bindingNormalOrder(DirectExchange directExchange,Queue queueNormalOrder){
        return BindingBuilder.bind(queueNormalOrder).to(directExchange).with("order");
    }

    //创建一个死信队列
    @Bean
    public Queue queueDlx(){
        return QueueBuilder.durable("queue.delayed1.dlx").build();
    }

    //将死信队列与交换机进行绑定
    @Bean
    public Binding bindingDlx(DirectExchange directExchange,Queue queueDlx){
        return BindingBuilder.bind(queueDlx).to(directExchange).with("error");
    }


}
