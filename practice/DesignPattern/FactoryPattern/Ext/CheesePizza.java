package com.sjh.DesignPattern.FactoryPattern.Ext;

public class CheesePizza extends Pizza {
    PizzaIngredientFactory factory;

    public CheesePizza(PizzaIngredientFactory factory) {
        this.factory = factory;
    }

    @Override
    void prepare() {
        System.out.println("Preparing " + name);
        sauce = factory.createSauce();
        dough = factory.createDough();
        cheese = factory.createCheese();
    }
}
