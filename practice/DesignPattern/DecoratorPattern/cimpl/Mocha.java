package com.sjh.DesignPattern.DecoratorPattern.cimpl;

import com.sjh.DesignPattern.DecoratorPattern.Beverage;
import com.sjh.DesignPattern.DecoratorPattern.CondimentDecorator;

/**
 * “调料装饰者”实现类：“摩卡调料”
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage; //被装饰者

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        return 0.20 + beverage.cost();
    }
}
