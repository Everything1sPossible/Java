package com.sjh.DesignPattern.CommandPattern;

/**
 * @author sjh
 * @Description: “接收者”实现类
 * @date 2018/11/25 20:39
 */
public class LightImpl implements Light {
    @Override
    public void on() {
        System.out.println("on..");
    }

    @Override
    public void off() {
        System.out.println("off..");
    }
}
