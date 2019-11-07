package com.zed.activemq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zeluo
 * @date 2019/11/7 10:07
 */
@Configuration
public class MQConfig {

    @Value("${activemq.max-threads}")
    public String maxThread;

    @Value("${activemq.min-threads}")
    public String minThread;

    @Value("${activemq.queue-name}")
    public String queueName;
}
