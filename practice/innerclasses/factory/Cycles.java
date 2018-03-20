package com.sjh.thinkinginjava.innerclasses.factory;

public class Cycles {
    public static void getCycles(CycleFactory factory) {
        Cycle c = factory.getCycle();
        System.out.println(c.getName());
    }

    public static void main(String[] args) {
        getCycles(Unicycle.factory);
        getCycles(Bicycle.factory);
        getCycles(Tricycle.factory);
    }
}
