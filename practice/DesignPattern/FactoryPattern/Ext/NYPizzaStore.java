package com.sjh.DesignPattern.FactoryPattern.Ext;



public class NYPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        NYPizzaIngredientFactory factory = new NYPizzaIngredientFactory();
        if (type != null && type.equals("cheese")) {
            pizza = new CheesePizza(factory);
            pizza.setName("New York Style Cheese Pizza..");
            return pizza;
        }
        return null;
    }
}
