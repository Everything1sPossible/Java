package com.sjh.DesignPattern.ProxyPattern.proxy2;


import com.sjh.DesignPattern.ProxyPattern.proxy1.Interface;
import com.sjh.DesignPattern.ProxyPattern.proxy1.RealObject;

public class SimpleDynamicProxy {
    public static void consumer(Interface inter) {
        inter.doSomething();
//        inter.somethingElse("hello");
    }

    public static void main(String[] args) {
        RealObject realObject = new RealObject();
        consumer(realObject);
//        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
//                new Class[]{Interface.class}, new DynamicProxyHandler(realObject));
        Interface proxy = (Interface) new DynamicProxyHandler(realObject)
                .getProxy(Interface.class.getClassLoader(),new Class[]{Interface.class});
        consumer(proxy);
    }
}
