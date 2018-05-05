package com.sjh.DesignPattern.FactoryPattern;

public class Test {
    public static void main(String[] args) {
        PizzaStore pizzaStore = new NYPizzaStroe();
        PizzaStore pizzaStore1 = new ChicagoPizzaStore();

        Pizza pizza = pizzaStore.orderPizza("cheese");
        System.out.println(pizza.getName() + "\n");
        Pizza pizza1 = pizzaStore1.orderPizza("cheese");
        System.out.println(pizza1.getName() + "\n");
    }
}
