package com.sjh.DesignPattern.FactoryPattern;

public class NYStyleCheesePizza extends Pizza {
    public NYStyleCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
        sauce = "Marinara Sauce";
        dough = "Thin Crust Dough";
        toppings.add("Grated Reggiano Cheese");
    }
}
