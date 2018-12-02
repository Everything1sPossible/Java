package com.sjh.DesignPattern.TemplatePattern;

/**
 * @author sjh
 * @Title: Coffee
 * @ProjectName com.sjh.DesignPattern.TemplatePattern
 * @Description: TODO
 * @date 2018/12/2 22:10
 */
public class Coffee extends CaffeineBeverage {
    @Override
    public void brew() {
        System.out.println("Dripping Coffee through filter..");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding Sugar and Milk..");
    }
}
