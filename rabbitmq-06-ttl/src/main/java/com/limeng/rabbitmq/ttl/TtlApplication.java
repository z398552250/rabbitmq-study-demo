package com.limeng.rabbitmq.ttl;

import com.limeng.rabbitmq.ttl.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TtlApplication implements ApplicationRunner {

    @Autowired
    private SendMessageService sendMessageService;

    public static void main(String[] args) {
        SpringApplication.run(TtlApplication.class,args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sendMessageService.send1();
        sendMessageService.send3();
        sendMessageService.send2();

    }
}
