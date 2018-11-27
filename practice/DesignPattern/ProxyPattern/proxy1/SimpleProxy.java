package com.sjh.DesignPattern.ProxyPattern.proxy1;

public class SimpleProxy implements Interface {
    private Interface inter;

    public SimpleProxy(Interface inter) {
        this.inter = inter;
    }

    @Override
    public void doSomething() {
        System.out.println("SimpleProxy doSomething");
        inter.doSomething();
    }

    @Override
    public void somethingElse(String args) {
        System.out.println("SimpleProxy somethingElse " + args);
        inter.somethingElse(args);
    }
}
