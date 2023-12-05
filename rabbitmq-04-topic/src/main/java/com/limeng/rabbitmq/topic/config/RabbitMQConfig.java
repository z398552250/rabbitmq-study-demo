package com.limeng.rabbitmq.topic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //创建一个主题交换机,名称为exchange.topic
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("exchange.topic");
    }


    //创建一个队列A,名称为queue.topic.a
    @Bean
    public Queue queueA(){
        return new Queue("queue.topic.a");
    }

    //创建一个队列B,名称为queue.topic.b
    @Bean
    public Queue queueB(){
        return new Queue("queue.topic.b");
    }

    //将队列A与交换机进行绑定,路由键设置模糊匹配,示例apple.fruits.sweet.red
    @Bean
    public Binding bindingA1(TopicExchange topicExchange,Queue queueA){
        return BindingBuilder.bind(queueA).to(topicExchange).with("*.fruits.#");
    }

    //将队列A与交换机进行绑定,路由键设置模糊匹配,示例chicken.food.meat.haochi
    @Bean
    public Binding bindingA2(TopicExchange topicExchange,Queue queueA){
        return BindingBuilder.bind(queueA).to(topicExchange).with("*.food.#");
    }

    //将队列B与几下换机进行绑定,路由键设置模糊匹配,示例dug.animal
    @Bean
    public Binding bindingB(TopicExchange topicExchange,Queue queueB){
        return BindingBuilder.bind(queueB).to(topicExchange).with("*.animal");
    }



}
