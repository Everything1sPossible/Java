package com.sjh.thinkinginjava.handleobjectExt;

import java.util.WeakHashMap;

public class WeakHashMapTest {
    public static void main(String[] args) {
        int size = 100;
        Key[] keys = new Key[size];
        WeakHashMap<Key, Value> weakHashMap = new WeakHashMap<Key, Value>();
        for(int i = 0; i < size; i++) {
            Key k = new Key(Integer.toString(i));
            Value v = new Value(Integer.toString(i));
            if(i % 3 == 0) {
                keys[i] = k;
            }
            weakHashMap.put(k, v);
        }
        System.gc();
        System.out.println("GC.......");
        System.out.println(weakHashMap);
    }
}
