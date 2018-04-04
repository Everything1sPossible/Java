package com.sjh.thinkinginjava.io.serialize;

import java.io.Serializable;

public class Data implements Serializable {
    private int n;

    public Data(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return Integer.toString(n);
    }

}
