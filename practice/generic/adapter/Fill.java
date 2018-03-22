package com.sjh.thinkinginjava.generic.adapter;

import com.sjh.thinkinginjava.generic.Generator;

public class Fill {
    public static <T> void fill(Addable<T> addable, Class<? extends T> clazz, int size) {
        for(int i = 0; i < size; i++ ) {
            try {
                addable.add(clazz.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    public static <T> void fill(Addable<T> addable, Generator<T> generator, int size) {
        for(int i = 0; i < size; i++ ) {
            addable.add(generator.next());
        }
    }
}
