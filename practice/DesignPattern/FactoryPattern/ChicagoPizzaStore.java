package com.sjh.DesignPattern.FactoryPattern;

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        if (type != null && type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        }
        return null;
    }
}
