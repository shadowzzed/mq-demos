package com.zed.rabbitmq.autoAck;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zeluo
 * @date 2019/8/26 10:19
 */
@Configuration
public class Config {
    private static final String QUEUE_NAME = "test.test";

    private static final String TOPICEXCHANGE_NAME = "exchange";

    @Bean("queue")
    public Queue queue () {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPICEXCHANGE_NAME);
    }

    @Bean
    public Binding bindingExchange(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("*.test");
    }
}
