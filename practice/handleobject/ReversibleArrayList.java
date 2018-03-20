package com.sjh.thinkinginjava.handleobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ReversibleArrayList<T> extends ArrayList<T> {
    public ReversibleArrayList(Collection<? extends T> c) {
        super(c);
    }
    public Iterable<T> reversed() {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    int current = size() - 1;
                    public boolean hasNext() {
                        return current > -1;
                    }
                    public T next() {
                        return get(current--);
                    }
                    public void remove() {

                    }
                };
            }
        };
    }
    public static void main(String[] args) {
        ReversibleArrayList<String> reversibleArrayList =
                new ReversibleArrayList<String>(Arrays.asList("a b c".split(" ")));
        for(String s : reversibleArrayList.reversed()) {
            System.out.print(s + " ");
        }
        System.out.println();
        for(String s : reversibleArrayList) {
            System.out.print(s + " ");
        }
    }
}
