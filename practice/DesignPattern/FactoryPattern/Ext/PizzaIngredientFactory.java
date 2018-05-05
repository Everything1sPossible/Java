package com.sjh.DesignPattern.FactoryPattern.Ext;

/**
 * “原料工厂”接口
 */
public interface PizzaIngredientFactory {
    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
}
