package com.limeng.rabbitmq.delayed1;

import com.limeng.rabbitmq.delayed1.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Delayed1Application implements ApplicationRunner {
    @Autowired
    private SendMessageService sendMessageService;

    public static void main(String[] args) {
        SpringApplication.run(Delayed1Application.class,args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sendMessageService.order();
        sendMessageService.pay();
    }
}
