package com.sjh.DesignPattern.SingletonPattern;

/**
 * 静态内部类机制实现单例模式
 */
public class Singleton2 {
    static class SingletonInstance {
        private static Singleton2 singleton2 = new Singleton2();
    }
    public Singleton2 getInstance () {
        return SingletonInstance.singleton2;
    }
}
