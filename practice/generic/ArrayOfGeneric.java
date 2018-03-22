package com.sjh.thinkinginjava.generic;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArrayOfGeneric<T> {
    static final int SIZE = 100;
    static Generic<Integer>[] gia;
    private T[] array;

    public ArrayOfGeneric(Class<T> type, int size) {
//        this.array = (T[]) Array.newInstance(type, size);
        this.array = (T[])new Object[size]; //ClassCastException
    }

    public T[] rep(){return array;}
    public static void main(String[] args) {
        gia = (Generic<Integer>[])new Generic[SIZE];
        gia[0] = new Generic<Integer>();
        ArrayOfGeneric<String> aog = new ArrayOfGeneric<String>(String.class, 10);
        String[] argss = aog.rep();
    }
}

class Generic<T>{}
