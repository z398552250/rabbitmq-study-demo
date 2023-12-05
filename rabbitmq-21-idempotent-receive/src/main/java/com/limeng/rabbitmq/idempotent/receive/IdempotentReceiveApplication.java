package com.limeng.rabbitmq.idempotent.receive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdempotentReceiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdempotentReceiveApplication.class,args);
    }
}
