package com.limeng.rabbitma.reliability.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange directExchange(){
        return ExchangeBuilder.directExchange("exchange.reliability").build();
    }

    @Bean
    public Queue queueNormal(){
        return QueueBuilder.durable("queue.reliability.normal")
                .deadLetterExchange("exchange.reliability")
                .deadLetterRoutingKey("error")
                .build();
    }

    @Bean
    public Queue queueDlx(){
        return QueueBuilder.durable("queue.reliability.dlx").build();
    }

    @Bean
    public Binding bindingNormal(DirectExchange directExchange,Queue queueNormal){
        return BindingBuilder.bind(queueNormal).to(directExchange).with("order");
    }

    @Bean
    public Binding bindingDlx(DirectExchange directExchange,Queue queueDlx){
        return BindingBuilder.bind(queueDlx).to(directExchange).with("error");
    }


}
