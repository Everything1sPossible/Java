package com.sjh.thinkinginjava.innerclasses;

public class DotThis2 {
    void f() {
        System.out.println("DotThis.f()..");
    }
    public static class Inner {
        public DotThis2 outer() {
            return new DotThis2();
        }
    }
    public static void main(String[] args) {
        DotThis2 d = new DotThis2();
        /**
         * 此处Inner内部类是静态内部类(嵌套类),创建对象的写法变成下面这种形式;
         * 如果在外部类中使用,可以直接简写成Inner i = new Inner();
         * 如果在其他的类中创建该内部类对象,则DotThis2.Inner i = new DotThis2.Inner();
         * 注意:
         *  1.要创建嵌套类的对象,并不需要期外部类的对象
         *  2.不能从嵌套类的对象中访问非静态的外部类对象
         *  3.嵌套类可以有static数据和static字段
         */
        DotThis2.Inner i = new DotThis2.Inner();
        i.outer().f();
    }
}
