package com.sjh.thinkinginjava.classTest.proxy1;

public class SimpleProxy implements Interface {
    private Interface inter;

    public SimpleProxy(Interface inter) {
        this.inter = inter;
    }

    public void doSomething() {
        System.out.println("SimpleProxy doSomething");
        inter.doSomething();
    }

    public void somethingElse(String args) {
        System.out.println("SimpleProxy somethingElse " + args);
        inter.somethingElse(args);
    }
}
