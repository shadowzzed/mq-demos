package com.zed.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Zeluo
 * @date 2019/8/26 10:13
 */
@Component
public class Consumer {
    @JmsListener(destination = "testDemo")

    public void receive(String message) {
        System.out.println(message);
    }
}
