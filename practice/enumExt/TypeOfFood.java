package com.sjh.thinkinginjava.enumExt;

public class TypeOfFood {
    public static void main(String[] args) {
        Food food = Food.Appetizer.SALAD;
        food = Food.Coffee.BLACK_COFFEE;
        food = Food.Dessert.FRUIT;
        food = Food.MainCourse.BURRITO;
    }
}
