package com.sjh.DesignPattern.DecoratorPattern.cimpl;

import com.sjh.DesignPattern.DecoratorPattern.Beverage;
import com.sjh.DesignPattern.DecoratorPattern.CondimentDecorator;

/**
 * “调料装饰者”实现类：“奶泡调料”
 */
public class Whip extends CondimentDecorator{
    Beverage beverage; //被装饰者

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    @Override
    public double cost() {
        return 0.15 + beverage.cost();
    }
}
