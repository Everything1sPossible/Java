package com.sjh.thinkinginjava.classTest.proxy2;

import com.sjh.thinkinginjava.classTest.proxy1.Interface;
import com.sjh.thinkinginjava.classTest.proxy1.RealObject;

import java.lang.reflect.Proxy;

public class SimpleDynamicProxy {
    public static void consumer(Interface inter) {
        inter.doSomething();
//        inter.somethingElse("hello");
    }

    public static void main(String[] args) {
        RealObject realObject = new RealObject();
        consumer(realObject);
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
                new Class[]{Interface.class}, new DynamicProxyHandler(realObject));
        consumer(proxy);
    }
}
