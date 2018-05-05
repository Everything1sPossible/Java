package com.sjh.DesignPattern.DecoratorPattern.cimpl;

import com.sjh.DesignPattern.DecoratorPattern.Beverage;
import com.sjh.DesignPattern.DecoratorPattern.CondimentDecorator;

/**
 * “调料装饰者”实现类：“豆浆调料”
 */
public class Soy extends CondimentDecorator{
    Beverage beverage; //被装饰者

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    @Override
    public double cost() {
        return 0.25 + beverage.cost();
    }
}
