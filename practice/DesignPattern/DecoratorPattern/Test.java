package com.sjh.DesignPattern.DecoratorPattern;

import com.sjh.DesignPattern.DecoratorPattern.bimpl.DarkRoast;
import com.sjh.DesignPattern.DecoratorPattern.bimpl.Espresso;
import com.sjh.DesignPattern.DecoratorPattern.cimpl.Mocha;
import com.sjh.DesignPattern.DecoratorPattern.cimpl.Whip;

public class Test {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();//没有任何装饰的“浓缩咖啡”
        System.out.println(beverage.getDescription() + " $" +beverage.cost());

        Beverage beverage1 = new DarkRoast();//“深焙咖啡”
        beverage1 = new Mocha(beverage1); //“摩卡”装饰
        beverage1 = new Mocha(beverage1); //“摩卡”装饰
        beverage1 = new Whip(beverage1);  //“奶泡”装饰
        System.out.println(beverage1.getDescription() + " $" +beverage1.cost());
    }
}
