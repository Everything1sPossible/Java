package com.sjh.thinkinginjava.handleobjectExt;

public class VeryBig {
    private static final int SIZE = 10000;
    private long[] la = new long[SIZE];
    private String id;


    public VeryBig(String id) {
        this.id = id;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize " + id);
    }
}
