package com.sjh.DesignPattern.FactoryPattern;

public class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Deep Dish Cheese Pizza";
        sauce = "Plum Tomato Sauce";
        dough = "Extra Thick Crust Dough";
        toppings.add("Shredded Mozzarella Cheese");
    }
    void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
