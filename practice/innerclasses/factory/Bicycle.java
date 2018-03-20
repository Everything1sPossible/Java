package com.sjh.thinkinginjava.innerclasses.factory;

public class Bicycle implements Cycle {
    public static CycleFactory factory = new CycleFactory() {
        public Cycle getCycle() {
            return new Bicycle();
        }
    };
    public String getName() {
        return "Bicycle";
    }
}
