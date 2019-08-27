package com.zed.rabbitmqmanual.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Zeluo
 * @date 2019/8/27 9:54
 * 手动连接到rabbit的工具类
 */
public class ChannelUtil {
    private static ConnectionFactory connectionFactory;
    static {
        connectionFactory = new ConnectionFactory();
    }
    public static Channel getChannel(String add) throws IOException, TimeoutException {
        connectionFactory.setHost(add);
        Connection connection = connectionFactory.newConnection();
        return connection.createChannel();
    }
}
