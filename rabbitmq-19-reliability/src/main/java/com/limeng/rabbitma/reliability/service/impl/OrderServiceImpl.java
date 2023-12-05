package com.limeng.rabbitma.reliability.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.limeng.rabbitma.reliability.po.OrderPo;
import com.limeng.rabbitma.reliability.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Integer add(OrderPo orderPo) {
        String exchange="exchange.reliability";
        String routingKey="order";
        //1. 处理库存业务(略)
        log.info("处理库存业务逻辑");
        //2. 处理后续业务,举例:订单处理完成后,下发短信并且增加计分等等一些边缘业务,此时可以使用mq将消息通知到对应的服务.然后此刻及时结束服务,响应客户端
        try {
            String orderStr = objectMapper.writeValueAsString(orderPo);
            Message message = MessageBuilder.withBody(orderStr.getBytes()).build();
            CorrelationData correlationData=new CorrelationData();
            ReturnedMessage returnedMessage=new ReturnedMessage(message,0,null,exchange,routingKey);
            correlationData.setId(orderPo.getOrderId());
            correlationData.setReturned(returnedMessage);
            rabbitTemplate.convertAndSend(exchange,routingKey,message,correlationData);
            log.info("消息发送完成");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return 1;
    }
}
