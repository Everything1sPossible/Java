package com.sjh.thinkinginjava.handleobjectExt;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Maps {
    public static void printKeys(Map<Integer, String> map) {
        System.out.print(" Size = " + map.size() + " , ");
        System.out.print("Keys: " );
        System.out.print(map.keySet());
        System.out.println();
    }
    public static void test(Map<Integer, String> map) {
        System.out.print(map.getClass().getSimpleName());
        map.putAll(new CountingMapData(25));
        printKeys(map);
        System.out.print("Values: ");
        System.out.print(map.values());
        System.out.println();
        System.out.print("Map: " + map);
    }

    public static void main(String[] args) {
        Maps.test(new HashMap<Integer, String>());
        Maps.test(new TreeMap<Integer, String>());
        Maps.test(new LinkedHashMap<Integer, String>());
        Maps.test(new IdentityHashMap<Integer, String>());
        Maps.test(new ConcurrentHashMap<Integer, String>());
        Maps.test(new WeakHashMap<Integer, String>());
    }
}
