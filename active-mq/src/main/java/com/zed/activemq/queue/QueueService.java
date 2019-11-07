package com.zed.activemq.queue;

import org.springframework.stereotype.Service;

/**
 * @author Zeluo
 * @date 2019/11/5 17:42
 */
@Service
public interface QueueService {
    int getMsgNumbers(String queueName);
}
