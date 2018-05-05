package com.sjh.DesignPattern.DecoratorPattern.bimpl;

import com.sjh.DesignPattern.DecoratorPattern.Beverage;

/**
 * “饮料”实现类：“综合咖啡”
 */
public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "HouseBlend";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
