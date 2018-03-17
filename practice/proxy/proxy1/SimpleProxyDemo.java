package com.sjh.thinkinginjava.classTest.proxy1;

public class SimpleProxyDemo {
    public static void consumer(Interface inter) {
        inter.doSomething();
        inter.somethingElse("hello");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}
