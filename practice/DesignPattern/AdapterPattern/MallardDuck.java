package com.sjh.DesignPattern.AdapterPattern;

/**
 * @author sjh
 * @Title: MallardDuck
 * @ProjectName com.sjh.DesignPattern.AdapterPattern
 * @Description: TODO
 * @date 2018/11/27 19:05
 */
public class MallardDuck implements Duck {
    @Override
    public void fly() {
        System.out.println("i am fly..");
    }

    @Override
    public void quack() {
        System.out.println("i am quick..");
    }
}
