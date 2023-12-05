package com.limeng.rabbitmq.delayed2.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    //创建一个自定义交换机,使用是延迟交换机插件
    //延迟交换机不建议在消息量大的情况下使用,因为其底层使用的是mnesia小型数据库
    @Bean
    public CustomExchange customExchange(){
        Map<String,Object> arguments=new HashMap<>();
        arguments.put("x-delayed-type","direct");
        return new CustomExchange("exchange.delayed2","x-delayed-message",true,false,arguments);
    }

    //创建一个队列
    @Bean
    public Queue queue(){
        return QueueBuilder.durable("queue.delayed2").build();
    }

    //将队列与延迟交换机进行绑定,注意后面的noargs()方法,这里与普通绑定模式不同
    @Bean
    public Binding binding(CustomExchange customExchange,Queue queue){
        return BindingBuilder.bind(queue).to(customExchange).with("info").noargs();
    }


}
