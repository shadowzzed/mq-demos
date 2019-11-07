package com.zed.activemq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Zeluo
 * @date 2019/8/26 10:13
 */
@Component
@Slf4j
public class Consumer {
    @JmsListener(destination = "testDemo",concurrency = "5-50")
    public void receive(String message) throws InterruptedException {
        Thread.sleep(200L);
        log.info(message);
    }
}
