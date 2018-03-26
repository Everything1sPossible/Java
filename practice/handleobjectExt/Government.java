package com.sjh.thinkinginjava.handleobjectExt;

import com.sjh.thinkinginjava.generic.Generator;

public class Government implements Generator<String> {
    private String[] foundation = ("Anti Tracks is a complete solution to protect your privacy and enhance your PC performance. ")
            .split(" ");
    private int index;
    public String next() {
        return foundation[index++];
    }
}
