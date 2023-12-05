package com.limeng.rabbitmq.exchange.properties.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public DirectExchange directExchange(){
        return ExchangeBuilder.directExchange("exchange.properties.direct")
                //设置交换机在最后一个队列解绑后自动删除
                .autoDelete()
                //设置交换机非持久化,在rabbitmq服务器重启后,自动删除
                .durable(false)
                //指定该交换机的备用交换机,在该交换机无法将消息发送至任意队列时,自动转发至备用交换机
                .alternate("exchange.properties.fanout")
                .build();
    }

    @Bean
    public Queue queueNormal(){
        return QueueBuilder.durable("queue.properties.normal").build();
    }

    @Bean
    public Binding bindingNormal(DirectExchange directExchange,Queue queueNormal){
        return BindingBuilder.bind(queueNormal).to(directExchange).with("info");
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return ExchangeBuilder.fanoutExchange("exchange.properties.fanout")
                //设置该交换机只能由其它交换机连接,生产者无法直接发送消息至此交换机
                .internal()
                .build();
    }

    @Bean
    public Queue queueAlternate(){
        return QueueBuilder.durable("queue.properties.alternate").build();
    }

    @Bean
    public Binding bindingAlternate(FanoutExchange fanoutExchange,Queue queueAlternate){
        return BindingBuilder.bind(queueAlternate).to(fanoutExchange);
    }






}
