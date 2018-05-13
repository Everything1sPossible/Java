package com.sjh.pubsub;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sjh.util.ConnectionUtils;

/**
 *  发布-订阅模式
 *  RabbitMQ的消息模型的核心思想是,生产者没有直接向队列发送任何消息。
 *  实际上,经常生产者甚至不知道一个消息将传递给任何队列。事实上，Producer
 *  只能发送message给exchange。exchange 很简单，一方面它从producers 接收
 *  messages,另一方面，它把messages 推送给queues。因此exchange要知道怎么
 *  处理接收到的message。是把message发给一个特定的队列?还是发给多个队列?或者
 *  丢弃？这个规则是由 exchange type 定义的。
 *
 *  Exchange 有以下几种：direct, topic, headers 和 fanout。
 *  1.fanout类型的Exchange路由规则非常简单，它会把所有发送到该Exchange的消息路由到所有
 *    与它绑定的Queue中。
 *  2.direct类型的Exchange路由规则也很简单，它会把消息路由到那些Binding key与Routing key
 *    完全匹配的Queue中。
 *  3.topic类型的Exchange在匹配规则上进行了扩展，它与direct类型的Exchage相似，
 *    也是将消息路由到Binding Key与Routing Key相匹配的Queue中，但这里的匹配规则
 *    有些不同，它约定：Routing Key为一个句点号“.”分隔的字符串（我们将被句点号". "分
 *    隔开的每一段独立的字符串称为一个单词），如"stock.usd.nyse"、"nyse.vmw"、
 *    "quick.orange.rabbit"。Binding Key与Routing Key一样也是句点号“. ”分隔的
 *    字符串。Binding Key中可以存在两种特殊字符"*"与"#"，用于做模糊匹配，其中"*"用
 *    于匹配一个单词，"#"用于匹配多个单词（可以是零个）。
 *  4.headers类型的Exchange不依赖于Routing Key与Binding Key的匹配规则来路由消息，
 *    而是根据发送的消息内容中的headers属性进行匹配。在绑定Queue与Exchange时指定一
 *    组键值对；当消息发送到Exchange时，RabbitMQ会取到该消息的headers（也是一个键值对
 *    的形式），对比其中的键值对是否完全匹配Queue与Exchange绑定时指定的键值对。如果完
 *    全匹配则消息会路由到该Queue，否则不会路由到该Queue。
 */
public class Send {
    private static final String EXCHANGE_NAME = "exchange_test";
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机fanout类型
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        String msg = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + msg + "'");

        channel.close();
        connection.close();
    }
}
