package com.example.demowebjms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableJms
public class DemoWebJmsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebJmsApplication.class, args);
    }

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("sending message ............");
        jmsTemplate.convertAndSend("Queue-1", "hello there");
        System.out.println("send message .......................");
    }
}
