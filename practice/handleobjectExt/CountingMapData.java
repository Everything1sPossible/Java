package com.sjh.thinkinginjava.handleobjectExt;

import java.util.HashMap;
import java.util.Random;

public class CountingMapData extends HashMap<Integer, String>{
    private String[] strs = ("Anti Tracks is a complete solution to protect your privacy and enhance your PC performance. ")
            .split(" ");
    public CountingMapData(int size) {
        for(int i = 0; i < size; i++) {
            put(i, strs[new Random().nextInt(strs.length)]);
        }
    }
}
