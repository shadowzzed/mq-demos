package com.zed.kafka.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zeluo
 * @date 2019/9/16 16:53
 */
@RestController
@RequestMapping("kafka")
public class ProducerTest {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @RequestMapping("")
    public String send(String msg) {
        kafkaTemplate.send("testDemo", msg);
        return "success";
    }
}
