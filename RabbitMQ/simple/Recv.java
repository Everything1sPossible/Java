package com.sjh.simple;

import com.rabbitmq.client.*;
import com.sjh.util.ConnectionUtils;

import java.io.IOException;

/**
 * 接收方
 */
public class Recv {
    private static final String QUEUE_NAME = "queue_test";
    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //获取声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //定义消费者，接收消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
