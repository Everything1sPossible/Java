package com.sjh.DesignPattern.ProxyPattern.proxy1;

public class RealObject implements Interface {
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String args) {
        System.out.println("somethingElse " + args);
    }
}
