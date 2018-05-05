package com.sjh.DesignPattern.DecoratorPattern.bimpl;

import com.sjh.DesignPattern.DecoratorPattern.Beverage;

/**
 * “饮料”实现类：“深焙咖啡”
 */
public class DarkRoast extends Beverage {
    public DarkRoast() {
        description = "DarkRoast";
    }

    @Override
    public double cost() {
        return 0.99;
    }
}
