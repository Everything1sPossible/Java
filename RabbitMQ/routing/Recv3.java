package com.sjh.routing;

import com.rabbitmq.client.*;
import com.sjh.util.ConnectionUtils;

import java.io.IOException;

/**
 * 持久化的交换机与持久化的队列之间通信会导致数据丢失。。。？？？
 */
public class Recv3 {
    private static final String QUEUE_NAME = "work_queue_test_durable";
    private static final String EXCHANGE_NAME = "exchange_direct_test_durable";
    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //获取通道
        final Channel channel = connection.createChannel();
        //获取声明队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        //将队列与交换机绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "error");
        channel.basicQos(1);

        //定义消费者，接收消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received3 '" + message + "'");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        /**
         * Ack是consumer 发送给RabbitMQ的，告诉它，task 已经接受，并处理了，RabbitMQ 可以删除它了。
         * 如果一个consumer死机了（channel closed,connection closed or Tcp connection lost），
         * 没有返回ack,RabbitMQ就会知道task 没有处理完，该task就会重新排队。
         * 如果这时候有另外一个Consumer在线，RabbitMQ 就会把它分发给他。
         */
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }
}
