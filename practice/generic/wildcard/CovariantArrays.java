package com.sjh.thinkinginjava.generic.wildcard;

import java.lang.reflect.Array;
import java.util.*;

public class CovariantArrays {
    public static void main(String[] args) {
        /**
         * Fruit
         * Apple extends Fruit
         * Orange extends Fruit
         * Jonathan extends Apple
         */
        Fruit[] fruits = new Apple[5];
        fruits[0] = new Apple();
        fruits[1] = new Jonathan();
//        fruits[2] = new Fruit(); //ArrayStoreException
//        fruits[3] = new Orange(); //ArrayStoreException
        System.out.println(Arrays.toString(fruits));
        Map<String, ?> map = new HashMap();
    }
}
