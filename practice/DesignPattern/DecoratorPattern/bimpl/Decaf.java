package com.sjh.DesignPattern.DecoratorPattern.bimpl;

import com.sjh.DesignPattern.DecoratorPattern.Beverage;

/**
 * “饮料”实现类：“低咖啡因咖啡”
 */
public class Decaf extends Beverage{
    public Decaf() {
        description = "Decaf";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
