package com.tsign.demo.transaction;

import com.tsign.util.ActiveMQUtil;

import javax.jms.*;

public class MQReceiveDemo {

    public static void main(String[] args) {
        receive();
    }

    public static void receive() {
        try {
            Connection connection = ActiveMQUtil.getConnection();
            //开启连接(必须执行此步骤，不然接收不到信息)
            connection.start();
            //获取会话
            //第一个参数:是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
            //第二个参数为false时，paramB的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
            //Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功。
            //Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
            //DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。
            Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            //目的地，其实就是连接到哪个队列，如果是点对点，那么它的实现是Queue，如果是订阅模式，那它的实现是Topic
            Destination destination = session.createQueue("test_queue");
            //消费者，就是消费数据的对象,从session中，获取一个消息消费者
            MessageConsumer consumer = session.createConsumer(destination);
            //实现一个消息的监听器
            //实现这个监听器后，以后只要有消息，就会通过这个监听器接收到
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        //获取到接收的数据
                        TextMessage textMessage = ((TextMessage)message);
                        String text = textMessage.getText();
                        System.out.println(text);
                        //手动回复确认
                        textMessage.acknowledge();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            //关闭接收端，也不会终止程序哦
//            consumer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
