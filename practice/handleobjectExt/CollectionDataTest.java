package com.sjh.thinkinginjava.handleobjectExt;

import java.util.LinkedHashSet;
import java.util.Set;

public class CollectionDataTest {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<String>(new CollectionData<String>(new Government(), 10));
//        set.addAll(CollectionData.list(new Government(), 10));
        System.out.println(set);
    }
}
