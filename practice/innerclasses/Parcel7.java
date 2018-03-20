package com.sjh.thinkinginjava.innerclasses;

public class Parcel7 {
    public Contents contents() {
        /**
         * 4.3.匿名内部类:创建一个继承自基类的或者实现接口(例:Contents)的匿名类的对象,
         *            通过new表达式返回的引用被自动向上转型为对Contents的引用.
         * 下面的例子使用了默认的构造器,如果基类需要一个有参数的构造器,只需在这样写即可:new Contents(x)
         */
        return new Contents(){
            private int i = 11;
            public int value() {return i;}
        };
    }
    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
        System.out.println(c.value());
    }
}
