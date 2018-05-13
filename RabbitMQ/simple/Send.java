package com.sjh.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sjh.util.ConnectionUtils;

/**
 * 发送方
 *
 * p---queue---c
 */
public class Send {
    private static final String QUEUE_NAME = "queue_test1";
    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //获取声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //发送信息
        String msg = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + msg + "'");

        channel.close();
        connection.close();
    }
}
