package com.sjh.thinkinginjava.handleobject;

import java.util.Iterator;
import java.util.Map;

public class IterableClass<T> implements Iterable<T> {
    private String[] words = "EDUCATION SHOULD ESCHEW OBFUSCATION".split(" ");
    public Iterator iterator() {
        return new Iterator() {
            private int index = 0;
            public boolean hasNext() {
                return index < words.length;
            }

            public Object next() {
                return words[index++];
            }

            public void remove() {

            }
        };
    }

    public static void main(String[] args) {
        for(String s : new IterableClass<String>()) {
            System.out.print(s + " ");
        }
        System.out.println();
        for(Map.Entry entry : System.getenv().entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue() + "||");
        }
    }
}
