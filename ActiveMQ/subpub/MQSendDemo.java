package com.tsign.demo.subpub;

import com.tsign.util.ActiveMQUtil;

import javax.jms.*;

/**
 * 订阅模式（一对多）：
 * 订阅/发布模式，同样可以有着多个发送端与多个接收端，但是接收端与发送端存在时间上的依赖，就是如果发送端发送消息的时候，
 * 接收端并没有监听消息，那么ActiveMQ将不会保存消息，将会认为消息已经发送，换一种说法，就是发送端发送消息的时候，接收端不在线，
 * 是接收不到消息的，哪怕以后监听消息，同样也是接收不到的。这个模式还有一个特点，那就是，发送端发送的消息，将会被所有的接收端给
 * 接收到，不类似点对点，一条消息只会被一个接收端给接收到。
 */
public class MQSendDemo {

    public static void main(String[] args) {
        send();
    }

    public static void send () {
        try {
            //获取连接
            Connection connection = ActiveMQUtil.getConnection();
            //开启连接
            connection.start();
            //获取会话
            //第一个参数:是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
            //第二个参数为false时，paramB的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
            //Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功。
            //Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
            //DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //目的地，其实就是连接到哪个队列，如果是点对点，那么它的实现是Queue，如果是订阅模式，那它的实现是Topic
            Destination destination = session.createTopic("test_topic");
            //生产者，就是产生数据的对象,从session中，获取一个消息生产者
            MessageProducer producer = session.createProducer(destination);
            //设置生产者的模式，有两种可选
            //DeliveryMode.PERSISTENT 当activemq关闭的时候，队列数据将会被保存
            //DeliveryMode.NON_PERSISTENT 当activemq关闭的时候，队列里面的数据将会被清空
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            //创建一条消息，当然，消息的类型有很多，如文字，字节，对象等,可以通过session.create..方法来创建出来
            TextMessage textMsg = session.createTextMessage();
            textMsg.setText("hello world3!");
            //生产者发送一条消息
            producer.send(textMsg);
            System.out.println("发送消息成功");
            //即便生产者的对象关闭了，程序还在运行哦
            producer.close();
            //将会话和连接全部关闭，程序终止
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
