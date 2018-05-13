package com.sjh.pubsub;

import com.rabbitmq.client.*;
import com.sjh.util.ConnectionUtils;

import java.io.IOException;

public class Recv2 {
    private static final String QUEUE_NAME = "work_queue_test_durable";
    private static final String EXCHANGE_NAME = "exchange_test";
    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //获取声明队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        //将队列与交换机绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        //定义消费者，接收消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received2 '" + message + "'");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        boolean autoAck = true;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }
}
