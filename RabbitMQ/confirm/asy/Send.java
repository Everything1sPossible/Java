package com.sjh.confirm.asy;

import com.rabbitmq.client.*;
import com.sjh.util.ConnectionUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Confirm 异步模式
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
 *
 * 异步confirm模式的编程实现最复杂，Channel对象提供的ConfirmListener()回调方法只包
 * 含deliveryTag（当前Chanel发出的消息序号），我们需要自己为每一个Channel维护一个
 * unconfirm的消息序号集合，每publish一条数据，集合中元素加1，每回调一次handleAck
 * 方法，unconfirm集合删掉相应的一条（multiple=false）或多条（multiple=true）记录。
 * 从程序运行效率上看，这个unconfirm集合最好采用有序集合SortedSet存储结构。
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
        //未确认的消息set集合
        final SortedSet<Long> unconfirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());

        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                //是否多条数据
                if (multiple) {
                    //返回此set的部分视图，其元素严格小于deliveryTag + 1并删除。
                    System.out.println("---multiple---true---");
                    unconfirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    //删除指定元素
                    System.out.println("---multiple---false---");
                    unconfirmSet.remove(deliveryTag);
                }
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("---multiple---true---");
                    unconfirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    System.out.println("---multiple---false---");
                    unconfirmSet.remove(deliveryTag);
                }
            }
        });

        String msg = "Hello World!";
        while (true) {
            long nextSeqNo = channel.getNextPublishSeqNo();
            channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
            unconfirmSet.add(nextSeqNo);
        }
    }
}
