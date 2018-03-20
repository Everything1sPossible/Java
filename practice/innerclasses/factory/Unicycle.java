package com.sjh.thinkinginjava.innerclasses.factory;

public class Unicycle implements Cycle {
    public static CycleFactory factory = new CycleFactory() {
        public Cycle getCycle() {
            return new Unicycle();
        }
    };
    public String getName() {
        return "Unicycle";
    }
}
