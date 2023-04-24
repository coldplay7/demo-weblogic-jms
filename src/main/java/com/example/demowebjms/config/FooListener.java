package com.example.demowebjms.config;

import lombok.SneakyThrows;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@EnableJms
@Component
public class FooListener {

    @SneakyThrows
    @JmsListener(
//            id = "outwardMessageListener",
//            containerFactory = "weblogicJmsListenerContainerFactory",
            destination = "Queue-1"
    )
    public void listen(TextMessage message) {
        System.out.println("listening message ........");
        System.out.println(message.getBody(String.class));
        System.out.println("got this message !!!!!!");
    }
}
