package com.sjh.thinkinginjava.innerclasses;

public class DotThis {
    void f() {
        System.out.println("DotThis.f()..");
    }
    protected class Inner {
        public DotThis outer() {
            return DotThis.this;//生成对外部类对象的引用
        }
    }
    public static void main(String[] args) {
        DotThis d = new DotThis();
        /**
         * 要想使用内部类的对象,必须使用外部类的对象来创建内部类对象;
         * 在拥有外部类对象之前是不可能创建内部类对象的.
         * 这是因为内部类对象会暗暗的连接到创建它的外部类对象上.
         * 但是如果创建的是嵌套类(静态内部类),则不需要对外部类对象的引用.
         */
        DotThis.Inner i = d.new Inner();
        i.outer().f();

        DotThis2 d2 = new DotThis2();
        DotThis2.Inner i2 = new DotThis2.Inner();
    }
}
