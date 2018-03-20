package com.sjh.thinkinginjava.handleobject;

import java.util.*;

public class MapData {
    public static Map map(Iterable iterable, int num) {
        Map map = new HashMap();
        Iterator iterator = iterable.iterator();
        while (iterator.hasNext()) {
            map.put(iterator.next(), num);
        }
        return map;
    }

    public static void main(String[] args) {
        List list = Arrays.asList(String.class, Double.class, Integer.class, Boolean.class);
        Map map = MapData.map(list, 0);
        Set<Map.Entry> set = map.entrySet();
        for (Map.Entry entry : set) {

        }
        System.out.println(map);
    }
}
