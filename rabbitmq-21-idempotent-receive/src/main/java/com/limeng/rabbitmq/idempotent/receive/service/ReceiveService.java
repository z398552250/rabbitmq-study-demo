package com.limeng.rabbitmq.idempotent.receive.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.limeng.rabbitmq.idempotent.receive.po.OrderPo;
import com.limeng.rabbitmq.idempotent.receive.utils.BloomFilter;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@Slf4j
public class ReceiveService {

    @Autowired
    private BloomFilter bloomFilter;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "queue.reliability.normal")
    public void receive(Channel channel, Message message) throws IOException {

        //1. 获取消息的唯一标识
        long messageId = message.getMessageProperties().getDeliveryTag();


        try {
            //2. 获取消息体
            String orderStr=new String(message.getBody());
            OrderPo orderPo = objectMapper.readValue(orderStr, OrderPo.class);
            //处理消息重复消费问题
            boolean check = bloomFilter.exists(orderPo.getOrderId());
            if (!check){
                //TODO 处理实际业务
                log.info("收到消息:{},开始处理业务",orderPo);
                //将数据添加至布隆过滤器
                bloomFilter.add(orderPo.getOrderId());
            }else {
                log.info("收到消息:{},判定为重复消息,不做业务处理",orderPo);
            }

//            Integer.parseInt("a");
            //3. 手动签收
            channel.basicAck(messageId,false);

        }catch (Exception e){
            //4. 不签收,进入死信队列
            channel.basicNack(messageId,false,false);
            log.error("业务处理异常,将消息丢弃至死信队列");
            throw new RuntimeException(e);
        }

    }
}
