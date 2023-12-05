package com.limeng.rabbitma.reliability.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQInit implements ApplicationRunner {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("rabbitmq初始化启动");
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                //生产者将消息发送至交换机,无论是否发送成功,都会回调此函数
                if (!ack){
                    //如果消息发送失败,则记录日志,并将消息记录至redis
                    ReturnedMessage returnedMessage = correlationData.getReturned();
                    String messageStr=new String(returnedMessage.getMessage().getBody());
                    log.error("消息发送至交换机失败,错误原因:{},消息内容:{}",cause,messageStr);
                    String messageId=correlationData.getId();
                    Boolean flag = redisTemplate.opsForValue().setIfAbsent("reliability:" + messageId, "");
                    if (flag){
                        //重试一次
                        rabbitTemplate.convertAndSend(returnedMessage.getExchange(),returnedMessage.getRoutingKey(),returnedMessage.getMessage(),correlationData);
                    }else {
                        // 将消息记录至redis
                        redisTemplate.opsForValue().set("error:"+messageId,messageStr);
                    }
                }
            }
        });

        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                //交换机将消息路由至队列,如果失败,则会调用此函数
                //记录错误信息至日志,并将消息记录至redis
                String messageStr=new String(returnedMessage.getMessage().getBody());
                log.error("消息路由至队列失败,错误原因:{},消息内容:{}",returnedMessage.getReplyText(),messageStr);
                // 这里不适合做重发策略,一种是使用此方式,将失败消息存储进redis,另外一种方式是使用备用交换机,将失败消息存储进备用队列
                // 这里将失败消息存储进redis
                redisTemplate.opsForList().rightPush("errorMsgList",messageStr);
            }
        });
    }
}
