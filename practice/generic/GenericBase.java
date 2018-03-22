package com.sjh.thinkinginjava.generic;

public class GenericBase<T> {
    private T obj;

    public void setObj(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }
}
