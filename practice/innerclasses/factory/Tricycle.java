package com.sjh.thinkinginjava.innerclasses.factory;

public class Tricycle implements Cycle {
    public static CycleFactory factory = new CycleFactory() {
        public Cycle getCycle() {
            return new Tricycle();
        }
    };
    public String getName() {
        return "Tricycle";
    }
}
