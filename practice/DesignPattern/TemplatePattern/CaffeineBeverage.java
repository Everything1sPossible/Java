package com.sjh.DesignPattern.TemplatePattern;

/**
 * @author sjh
 * @Title: CaffeineBeverage
 * @ProjectName com.sjh.DesignPattern.TemplatePattern
 * @Description: TODO
 * @date 2018/12/2 22:07
 */
public abstract class CaffeineBeverage {

    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    public abstract void brew();

    public abstract void addCondiments();

    public final void boilWater() {
        System.out.println("Boiling water..");
    }

    public final void pourInCup() {
        System.out.println("Pouring into cup..");
    }
}
