package com.sjh.thinkinginjava.generic.adapter;

import com.sjh.thinkinginjava.generic.Coffee;

import java.util.ArrayList;
import java.util.List;

public class FillTest {
    public static void main(String[] args) {
        List<Coffee> list = new ArrayList<Coffee>();
        Fill.fill(Adapter.collectionAdapter(list), Coffee.class, 2);
        Fill.fill(new AddableCollectionAdapter<Coffee>(list), Coffee.class, 3);
        for (Coffee coffee : list) {
            System.out.println(coffee);
        }
    }
}
