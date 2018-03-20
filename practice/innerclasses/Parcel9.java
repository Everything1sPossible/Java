package com.sjh.thinkinginjava.innerclasses;


import java.util.ArrayList;
import java.util.List;

public class Parcel9 {
    public Base base(int i) {
        return new Base(i) {
            /**
             * 4.6.一个匿名类,它通过实例初始化实现构造(匿名类不可能有构造器);
             * 此例中i不需要时final的,因为并没有在匿名类内部使用,只是传递给了基类的构造器;
             * 仅有且只能有一个这样的构造器,不可以重载实例化方法.
             */
            {
                System.out.println("Inside instance initializer");
            }
            @Override
            public void f() {
                System.out.println("In anonymous f()");
            }
        };
    }
    public static void main(String[] args) {
        Parcel9 p = new Parcel9();
        Base b = p.base(20);
        b.f();
        List list = new ArrayList();
    }
}
