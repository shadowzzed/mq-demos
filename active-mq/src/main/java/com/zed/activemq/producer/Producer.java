package com.zed.activemq.producer;


import com.zed.activemq.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Zeluo
 * @date 2019/8/26 10:12
 */
@Component
@EnableScheduling
public class Producer {
    @Autowired
    private ActiveMqService mqService;

    @Scheduled(fixedDelay = 3000)
    public void send() {
        this.mqService.send("testDemo", "I'M TEST");
    }
}
