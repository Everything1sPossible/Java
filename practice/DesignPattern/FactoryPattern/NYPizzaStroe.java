package com.sjh.DesignPattern.FactoryPattern;

public class NYPizzaStroe extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        if (type != null && type.equals("cheese")) {
            return new NYStyleCheesePizza();
        }
        return null;
    }
}
