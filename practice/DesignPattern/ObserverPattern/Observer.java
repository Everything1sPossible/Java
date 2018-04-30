package com.sjh.DesignPattern.ObserverPattern;

/**
 * “观察者”接口
 */
public interface Observer {

    //数据更新
    public void update(Weather weather);

}
