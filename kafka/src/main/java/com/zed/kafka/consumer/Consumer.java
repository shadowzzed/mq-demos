package com.zed.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Zeluo
 * @date 2019/9/16 16:56
 */
@Component
@Slf4j
public class Consumer {
    @KafkaListener(topics = {"testDemo"})
    public void receive(ConsumerRecord<String, String> record) {
        System.out.println(record.value());
    }
}
