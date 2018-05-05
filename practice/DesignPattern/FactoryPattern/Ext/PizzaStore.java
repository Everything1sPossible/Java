package com.sjh.DesignPattern.FactoryPattern.Ext;

import com.sjh.DesignPattern.FactoryPattern.Ext.Pizza;

public abstract class PizzaStore {
    public final Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.beke();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    public abstract Pizza createPizza(String type);

}
