package com.sjh.DesignPattern.ObserverPattern;

/**
 * “主题”接口
 */
public interface Subject {

    //注册观察者
    public void registerObserver(Observer o);

    //移除观察者
    public void removeObserver(Observer o);

    //状态改变时更新观察者
    public void notifyObserver();

}
