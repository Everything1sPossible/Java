package com.sjh.thinkinginjava.handleobjectExt;

import java.util.TreeMap;

public class SortedMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer, String> sortedMap = new TreeMap<Integer, String>();
        sortedMap.put(1, "a");
        sortedMap.put(2, "b");
        sortedMap.put(3, "c");
        sortedMap.put(4, "d");
        sortedMap.put(5, "e");
        System.out.println(sortedMap);
        Integer low = sortedMap.firstKey();
        Integer high = sortedMap.lastKey();
        System.out.println(low);
        System.out.println(high);
        System.out.println(sortedMap.subMap(low, high));
        System.out.println(sortedMap.headMap(high));
        System.out.println(sortedMap.tailMap(low));
    }
}
