package com.zed.activemq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Zeluo
 * @date 2019/8/26 11:28
 */
@Service
public class ActiveMqService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String queue, String message) {
        this.jmsTemplate.convertAndSend(queue, message);
    }
}
