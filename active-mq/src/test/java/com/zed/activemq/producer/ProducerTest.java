package com.zed.activemq.producer;

import com.zed.activemq.service.ActiveMqService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Zeluo
 * @date 2019/10/29 16:07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerTest {

    @Autowired
    ActiveMqService mqService;
    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            this.mqService.send("testDemo",String.valueOf(i));
        }
    }
}