package com.limeng.rabbitmq.confirm.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public DirectExchange directExchange(){
        return ExchangeBuilder.directExchange("exchange.confirm").build();
    }
    @Bean
    public Queue queue(){
        return QueueBuilder.durable("queue.confirm").build();
    }
    @Bean
    public Binding binding(DirectExchange directExchange,Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("info");
    }


}
