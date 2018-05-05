package com.sjh.DesignPattern.SingletonPattern;

/**
 * 枚举方式实现单例模式
 */
public enum Singleton3 {
    INSTANCE;

    public Singleton3 getInstance() {
        return Singleton3.INSTANCE;
    }
}
