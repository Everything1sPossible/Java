package com.sjh.thinkinginjava.generic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Derived<T> extends GenericBase<T> {

    public static void main(String[] args) throws Exception {
        Derived<String> derived = new Derived<String>();//此处泛型边界String
        derived.setObj("hello");//编译期检查传递进来的值是否是String类型
        String object = derived.getObj();//插入对传递出去的值的转型(String)
        System.out.println(object);
    }
}
