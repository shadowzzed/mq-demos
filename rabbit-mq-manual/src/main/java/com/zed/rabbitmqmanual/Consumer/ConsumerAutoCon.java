package com.zed.rabbitmqmanual.Consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Zeluo
 * @date 2019/8/27 9:55
 */
@Component
@RabbitListener(queues = "queue3.test")
public class ConsumerAutoCon {
    @RabbitHandler
    public void process(String message , Channel channel,  @Header(AmqpHeaders.DELIVERY_TAG)long tag) throws IOException {
        System.out.println(message);
        System.out.println(tag);
        tag++;
        try {
            channel.basicAck(tag,false);
            // 确认消息
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
