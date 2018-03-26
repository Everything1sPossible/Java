package com.sjh.thinkinginjava.handleobjectExt;

import java.util.LinkedHashMap;

public class LinkedHashMapDemo {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>(16, 0.75f, true);
        linkedHashMap.put(1, "A");
        linkedHashMap.put(2, "B");
        linkedHashMap.put(3, "C");
        linkedHashMap.put(4, "D");
        linkedHashMap.put(5, "E");
        linkedHashMap.put(6, "F");
        linkedHashMap.put(7, "G");
        linkedHashMap.put(8, "H");
        System.out.println(linkedHashMap);
        for(int i = 0; i < 6; i++) {
            linkedHashMap.get(i);
        }
        System.out.println(linkedHashMap);
        linkedHashMap.get(0);
        System.out.println(linkedHashMap);
    }
}
