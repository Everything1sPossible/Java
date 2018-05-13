package com.sjh.workfair;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sjh.util.ConnectionUtils;

/**
 * 公平分发策略
 *                |--c1
 * p-----queue----|
 *                |--c2
 * 1.默认，RabbitMQ会顺序的，平均的把任务发给每个consumer,到最后每个Consumer会得到相同数量的任务。
 * 2.一旦RabbitMQ分发message给Consumer,它就会立刻从内存删除。这种情况下，如果你关闭一个Worker,
 *   我们就会丢失他正在执行的消息。
 * 3.Ack是consumer 发送给RabbitMQ的，告诉它，task 已经接受，并处理了，RabbitMQ 可以删除它了。
 *   如果一个consumer死机了（channel closed,connection closed or Tcp connection lost），
 *   没有返回ack,RabbitMQ就会知道task 没有处理完，该task就会重新排队。如果这时候有另外一个Consumer在线，
 *   RabbitMQ 就会把它分发给他
 */
public class Send {

    private static final String QUEUE_NAME = "work_queue_test";
    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        /**
         * 获取声明队列
         * queue：队列名称
         * durable：是否持久化task，不允许重复定义同一个队列，否则报错
         */
        boolean durable = false;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        /**
         * 每个消费者发送确认消息之前，消息队列不把下一条消息发给消费者
         * 限制发送给同一个消费者，每次只发1(prefetchCount)条消息
         * 消费者方要把自动应答改为手动的。即autoAck = false
         */
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);
        //发送信息
        for (int i = 0; i < 20; i++) {
            String msg = "Hello " + i;
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + msg + "'");
            Thread.sleep(1);
        }

        channel.close();
        connection.close();
    }
}
