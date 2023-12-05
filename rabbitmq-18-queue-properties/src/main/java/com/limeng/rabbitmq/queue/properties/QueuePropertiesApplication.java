package com.limeng.rabbitmq.queue.properties;

import com.limeng.rabbitmq.queue.properties.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QueuePropertiesApplication implements ApplicationRunner {

    @Autowired
    private SendMessageService sendMessageService;

    public static void main(String[] args) {
        SpringApplication.run(QueuePropertiesApplication.class,args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        sendMessageService.send();
        sendMessageService.sendD();
        sendMessageService.sendE();

    }
}
