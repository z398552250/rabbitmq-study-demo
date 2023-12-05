package com.limeng.rabbitmq.dlx3.receive.service;

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
    public void receiveDlx3(Message message, Channel channel) throws IOException {
        //获取消息的唯一id
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            byte[] body = message.getBody();
            String str=new String(body);
            Integer.parseInt("aa");
            //参数说明:
            //  消息唯一id,
            //  是否确认之前的消息,true 是 false 否(只确认自己这一条)
            channel.basicAck(deliveryTag,false);
            log.info("消息处理正常,{}",str);
        }catch (Exception e){
            //参数说明:
            //  消息唯一id
            //  是否拒绝之前的消息, true 是 false 否(只拒绝自己这一条)
            //  是否将拒绝的消息重新入队 true 是 false 否
            channel.basicNack(deliveryTag,false,false);
            log.error("消费者程序异常,消息拒绝接收,重新入队,如果不重新入队,则进入死信队列");
            throw new RuntimeException(e);
        }

    }

}
