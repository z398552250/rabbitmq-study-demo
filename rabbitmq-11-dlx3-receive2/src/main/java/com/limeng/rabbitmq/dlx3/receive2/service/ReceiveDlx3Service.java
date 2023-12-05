package com.limeng.rabbitmq.dlx3.receive2.service;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class ReceiveDlx3Service {


    @RabbitListener(queues = "queue.dlx3.normal")
    public void receive(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            Integer.parseInt("aa");
            String str=new String(message.getBody());
            channel.basicAck(deliveryTag,false);
            log.info("接收消息正常:{}",str);
        }catch (Exception e){
            //参数说明:
            //  消息唯一id
            //  是否重新入队,true 是 false 否
            // basicReject与basicNack的区别是,basicNack可以批量处理之前的消息,basicReject不行
            channel.basicReject(deliveryTag,false);
            log.error("消费者处理消息异常");
            throw new RuntimeException(e);
        }

    }
}
