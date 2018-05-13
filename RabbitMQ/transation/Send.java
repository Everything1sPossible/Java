package com.sjh.transation;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sjh.util.ConnectionUtils;

/**
 * RabbitMQ的事务机制：
 * RabbitMQ中通过持久化机制保障了RabbitMQ服务挂掉之后数据丢失的问题。
 * RabbitMQ提供了两种方式用于生产者向RabbitMQ提供消息过程中的安全机制：
 *      AMQP实现了事务机制：有降低吞吐量等缺点
 *      Confirm模式
 *
 *  注意一点，已经在transaction事务模式的channel是不能再设置成confirm模式的，即这两种模式是不能共存的
 *
 *  txSelect：用户将当前channel设为transation模式
 *  txCommit：提交事务
 *  txCallback：回滚事务
 */
public class Send {
    private static final String EXCHANGE_NAME = "exchange_test";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机fanout类型
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        try {
            channel.txSelect();//开启事务
            String msg = "Hello World!";
            channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes("UTF-8"));

//            int x = 1 / 0;

            System.out.println(" [x] Sent '" + msg + "'");
            channel.txCommit();//提交事务
        } catch (Exception e) {
            channel.txRollback();//回滚事务
            System.out.println("方法报错。。事务回滚。。");
        }
        channel.close();
        connection.close();
    }
}
