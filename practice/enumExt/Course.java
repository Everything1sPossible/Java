package com.sjh.thinkinginjava.enumExt;

import java.util.Random;

public enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private Food[] foods;
    Course(Class<? extends Food> kind) {
        foods = kind.getEnumConstants();
    }
    public Food randomSelection() {
        return foods[new Random().nextInt(foods.length)];
    }
}
