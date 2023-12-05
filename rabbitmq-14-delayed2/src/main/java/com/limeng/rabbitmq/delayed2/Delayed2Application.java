package com.limeng.rabbitmq.delayed2;

import com.limeng.rabbitmq.delayed2.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Delayed2Application implements ApplicationRunner {
    @Autowired
    private SendMessageService sendMessageService;
    public static void main(String[] args) {
        SpringApplication.run(Delayed2Application.class,args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sendMessageService.pay();
        sendMessageService.order();
    }
}
