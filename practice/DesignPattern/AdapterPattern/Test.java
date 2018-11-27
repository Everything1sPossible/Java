package com.sjh.DesignPattern.AdapterPattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author sjh
 * @Title: Test
 * @ProjectName com.sjh.DesignPattern.AdapterPattern
 * @Description: TODO
 * @date 2018/11/27 19:08
 */
public class Test {
    public static void main(String[] args) {
//        System.out.println("======Duck=====");
//        Duck duck = new MallardDuck();
//        testDuck(duck);
//        System.out.println("======Turkey======");
//        Turkey turkey = new WildTurkey();
//        turkey.fly();
//        turkey.gobble();
//        System.out.println("======TurkeyAdapter=======");
//        Duck duck1 = new TurkeyAdapter(turkey);
//        testDuck(duck1);
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        Iterator iterator = list.iterator();
        iterator.forEachRemaining(x -> {
            System.out.println(x);
        });
    }

    public static void testDuck(Duck duck) {
        duck.fly();
        duck.quack();
    }

}
