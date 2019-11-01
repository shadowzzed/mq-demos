//package com.zed.rabbitmq.autoAck.producer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
///**
// * @author Zeluo
// * @date 2019/8/26 10:30
// */
//@Component
//@EnableScheduling
//public class Producer {
//    @Autowired
//    private RabbitMqService mqService;
//
//    @Scheduled(fixedDelay = 3000)
//    public void send() {
//        mqService.send("exchange", "queue1.test", "I'M TEST");
//    }
//}
