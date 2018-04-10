package com.sjh.thinkinginjava.enumExt;

public interface Food {
    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROOLS;
    }
    enum MainCourse implements Food {
        LASAGNE, BURRITO, PAD_THAT;
    }
    enum Dessert implements Food {
        TIRAMISU, GELATO, FRUIT;
    }
    enum Coffee implements Food {
        BLACK_COFFEE, TEA, HERB_TEA
    }

}
