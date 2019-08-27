package com.zed.rabbitmq.autoAck.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Zeluo
 * @date 2019/8/26 10:32
 */
@Component
@RabbitListener(queues = "test.test")
public class Consumer {
    @RabbitHandler
    public void receive(String message) {
        System.out.println(message);
    }
}
