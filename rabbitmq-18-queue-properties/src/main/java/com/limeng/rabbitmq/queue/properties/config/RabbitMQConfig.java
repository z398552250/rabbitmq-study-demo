package com.limeng.rabbitmq.queue.properties.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange directExchange(){
        return ExchangeBuilder.directExchange("exchange.queue.properties").build();
    }

    @Bean
    public Queue queueA(){
        return QueueBuilder.durable("queue.properties.a")
                //最后一个消费者断开连接,自动删除该队列
                .autoDelete()
                .build();
    }

    @Bean
    public Queue queueB(){
        //实验队列的持久化
        return new Queue("queue.properties.b",false);
    }

    @Bean
    public Queue queueC(){
        //设置只对第一次连接他的消费者可见，并且在连接断开时，自动删除
        return QueueBuilder.durable("queue.properties.c").exclusive().build();
    }

    @Bean
    public Queue queueD(){
//        当队列在指定的时间未被访问，则队列将被自动删除
        return QueueBuilder.durable("queue.properties.d").expires(15000).build();
    }

    @Bean
    public Queue queueE(){
        //设置队列最大消息为10个,并丢弃新的消息
        return QueueBuilder.durable("queue.properties.e").maxLength(10).overflow(QueueBuilder.Overflow.rejectPublish).build();
    }


    @Bean
    public Binding bindingA(DirectExchange directExchange,Queue queueA){
        return BindingBuilder.bind(queueA).to(directExchange).with("info");
    }

    @Bean
    public Binding bindingD(DirectExchange directExchange,Queue queueD){
        return BindingBuilder.bind(queueD).to(directExchange).with("d");
    }

    @Bean
    public Binding bindingE(DirectExchange directExchange,Queue queueE){
        return BindingBuilder.bind(queueE).to(directExchange).with("e");
    }

}
