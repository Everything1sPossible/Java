package com.sjh.confirm.simple;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sjh.util.ConnectionUtils;

/**
 * Confirm 普通模式
 * Confirm模式实现原理：
 *  1.生产者将信道设置成confirm模式，一旦信道进入confirm模式，所有在该信道上面发布
 *    的消息都将会被指派一个唯一的ID(从1开始)，一旦消息被投递到所有匹配的队列之后，
 *    broker就会发送一个确认给生产者(包含消息的唯一ID)，这就使得生产者知道消息已经
 *    正确到达目的队列了，如果消息和队列是可持久化的，那么确认消息会在将消息写入磁盘
 *    之后发出，broker回传给生产者的确认消息中delivery-tag域包含了确认消息的序列号，
 *    此外broker也可以设置basic.ack的multiple域，表示到这个序列号之前的所有消息都
 *    已经得到了处理；
 *  2.confirm模式最大的好处在于他是异步的，一旦发布一条消息，生产者应用程序就可以在
 *    等信道返回确认的同时继续发送下一条消息，当消息最终得到确认之后，生产者应用便可
 *    以通过回调方法来处理该确认消息，如果RabbitMQ因为自身内部错误导致消息丢失，就会
 *    发送一条nack消息，生产者应用程序同样可以在回调方法中处理该nack消息；
 * 编程模式：
 * 1.普通模式，每次发一条，waitForConfirms（）
 * 2.批量模式，每次发一批，waitForConfirms（）
 * 3.异步Confirm模式，提供一个回调方法
 */
public class Send {
    private static final String EXCHANGE_NAME = "exchange_test";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机fanout类型
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        //设置为confirm模式
        channel.confirmSelect();
        String msg = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + msg + "'");

        if (!channel.waitForConfirms()) {
            System.out.println("消息发送失败。。");
        } else {
            System.out.println("消息发送成功。。");
        }

        channel.close();
        connection.close();
    }
}
