package com.zed.activemq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Zeluo
 * @date 2019/10/30 15:15
 */
@Component
@Slf4j
public class ConsumerManual {

    @Bean
    public void consumer() {
        ExecutorService es = Executors.newFixedThreadPool(50);
        ConnectionFactory factory = null;
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;

        try {
            factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616?jms.prefetchPolicy.all=2");
            connection = factory.createConnection();

            connection.start();
            for (int i = 0; i < 50; i++) {
                es.execute(new MyRunnable(connection));
            }
//            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//            Destination destination = session.createQueue("testDemo");
//            consumer = session.createConsumer(destination);
//            int count = 0;
////            while (count < 10000) {
////                count++;
//                consumer.setMessageListener(new MessageListener() {
//                    public void onMessage(Message message) {
////                    System.out.println(1);
//                        TextMessage message1 = (TextMessage) message;
//                        try {
//                            Thread.sleep(200L);
//                            String text = message1.getText();
//                            log.info(text);
////                        System.out.println(text);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//            session.commit();
//            session.close();
//            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MyRunnable implements Runnable {

        private Connection connection;

        public MyRunnable(Connection connection) {
            this.connection = connection;
        }

        public void run() {

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
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}

