package com.limeng.rabbitmq.exchange.properties;

import com.limeng.rabbitmq.exchange.properties.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExchangePropertiesApplication implements ApplicationRunner {

    @Autowired
    private SendMessageService sendMessageService;

    public static void main(String[] args) {
        SpringApplication.run(ExchangePropertiesApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
//        sendMessageService.send1();
        sendMessageService.send2();
    }
}
