package com.sjh.thinkinginjava.enumExt;

public enum enumDemo {
    AA("a", 1),
    BB("b", 2);

    private String name;
    private int id;
    enumDemo(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
