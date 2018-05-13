package com.sjh.workfair;

import com.rabbitmq.client.*;
import com.sjh.util.ConnectionUtils;

import java.io.IOException;

public class Recv1 {
    private static final String QUEUE_NAME = "work_queue_test";
    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //获取通道
        final Channel channel = connection.createChannel();
        //获取声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicQos(1);

        //定义消费者，接收消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received1 '" + message + "'");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //改为手动应答
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        /**
         * Ack是consumer发送给RabbitMQ的，告诉它，task已经接受，并处理了，RabbitMQ 可以删除它了。
         * 如果一个consumer死机了（channel closed,connection closed or Tcp connection lost），
         * 没有返回ack,RabbitMQ就会知道task没有处理完，该task就会重新排队。
         * 如果这时候有另外一个Consumer在线，RabbitMQ 就会把它分发给他。
         */
        //此处设为false是因为公平分配策略，消费方需要手动应答
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }
}
