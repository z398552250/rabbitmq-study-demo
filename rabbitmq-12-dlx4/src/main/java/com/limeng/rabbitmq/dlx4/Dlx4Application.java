package com.limeng.rabbitmq.dlx4;

import com.limeng.rabbitmq.dlx4.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Dlx4Application implements ApplicationRunner {

    @Autowired
    private SendMessageService sendMessageService;

    public static void main(String[] args) {
        SpringApplication.run(Dlx4Application.class,args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sendMessageService.send();
    }
}
