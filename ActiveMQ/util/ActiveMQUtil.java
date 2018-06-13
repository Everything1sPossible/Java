package com.tsign.util;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

public class ActiveMQUtil {

    //用户名
    private static final String username = "admin";
    //密码
    private static final String password = "admin123";
    //链接地址
    private static final String baseUrl = "tcp://127.0.0.1:61616";

    //获取连接
    public static Connection getConnection() throws JMSException {
        //创建连接工厂
        ConnectionFactory factory = new ActiveMQConnectionFactory(username, password, baseUrl);
        //创建连接对象
        Connection connection = factory.createConnection();
        return connection;
    }
}
