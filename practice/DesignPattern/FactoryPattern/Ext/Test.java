package com.sjh.DesignPattern.FactoryPattern.Ext;

public class Test {
    public static void main(String[] args) {
        PizzaStore store = new NYPizzaStore();
        Pizza pizza = store.orderPizza("cheese");
        System.out.println(pizza.getName());
    }
}
