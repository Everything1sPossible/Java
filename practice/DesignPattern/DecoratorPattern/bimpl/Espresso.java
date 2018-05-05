package com.sjh.DesignPattern.DecoratorPattern.bimpl;

import com.sjh.DesignPattern.DecoratorPattern.Beverage;

/**
 * “饮料”实现类：“浓缩咖啡”
 */
public class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
