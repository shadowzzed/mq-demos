package com.zed.rabbitmqmanual.Consumer;

import com.rabbitmq.client.*;
import com.zed.rabbitmqmanual.utils.ChannelUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Zeluo
 * @date 2019/8/27 10:00
 */
@Component
@EnableScheduling
public class ConsumerManualCon {

    private static final String add = "localhost";
    @Scheduled(cron = "05 10 * * * ? ")
    public void processMessage() throws IOException, TimeoutException {
        final Channel channel = ChannelUtil.getChannel("localhost");
        //设置最大分配为1 防止堆积
        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message =new String(body,"UTF-8");
                System.out.println(message);
                channel.basicAck(envelope.getDeliveryTag(),false);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

    }
}
