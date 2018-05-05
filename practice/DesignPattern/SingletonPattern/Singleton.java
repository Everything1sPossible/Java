package com.sjh.DesignPattern.SingletonPattern;

/**
 * 双重锁机制实现单例模式
 */
public class Singleton {
    private volatile static Singleton singleton;
    private Singleton() {}
    public Singleton getInstance() {
        if(singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
