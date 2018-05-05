package com.sjh.DesignPattern.DecoratorPattern;

/**
 * “饮料”抽象类
 */
public abstract class Beverage {
    protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
