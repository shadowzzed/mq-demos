package com.zed.activemq.consumer;

import com.zed.activemq.MQConfig;
import com.zed.activemq.queue.QueueService;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Zeluo
 * @date 2019/10/30 15:15
 */
@Component
@Slf4j
public class ConsumerManual {

    @Autowired
    QueueService queueService;

    @Autowired
    MQConfig config;

    private static HashSet<MessageConsumer> set = new HashSet<MessageConsumer>(50);

    private static HashSet<com.zed.activemq.MessageListener> listeners = new HashSet<com.zed.activemq.MessageListener>(50);

    private static Connection connection = null;

    static {
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616?jms.prefetchPolicy.all=2");
            connection = factory.createConnection();
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public void initConsumer() {
        for (int i = 0; i < Integer.valueOf(config.minThread); i++) {
            this.createConsumer(config.queueName);
        }
    }


    @Scheduled(fixedDelay = 1000L)
    public void createConsumer() throws JMSException {
        if (queueService.getMsgNumbers(config.queueName) < 1000 || ConsumerManual.set.size() >= Integer.valueOf(config.maxThread))
            return;
        this.createConsumer(config.queueName);
    }

    //每10S检测一次是否需要销毁consumer
    @Scheduled(fixedDelay = 10000L)
    public void stopConsumer() {
        if (queueService.getMsgNumbers(config.queueName) < 100 || set.size() > Integer.valueOf(config.minThread) )
            return;
        Iterator<MessageConsumer> iterator = ConsumerManual.set.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            MessageConsumer consumer = iterator.next();
            if (count > 5) {
                //每次销毁前检查队列剩余数量
                if (queueService.getMsgNumbers(config.queueName) < 500) {
                    consumer = null;
                    iterator.remove();
                }

            }
            count++;
        }

    }

    private void createConsumer(String queueName) {
        try {
            com.zed.activemq.MessageListener listener = new com.zed.activemq.MessageListener();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(listener);
            set.add(consumer);
            listeners.add(listener);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    class MyRunnable implements Runnable {

        private Connection connection;

        public MyRunnable(Connection connection) {
            this.connection = connection;
        }

        public void run() {
//            DefaultMessageListenerContainer
            try {
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Destination destination = session.createQueue("testDemo");
                MessageConsumer consumer = session.createConsumer(destination);
                consumer.setMessageListener(new MessageListener() {
                    public void onMessage(Message message) {
                        try {
                            Thread.sleep(200L);
                            log.info(((TextMessage) message).getText());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                });
                set.add(consumer);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}

