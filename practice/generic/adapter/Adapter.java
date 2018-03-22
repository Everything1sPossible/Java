package com.sjh.thinkinginjava.generic.adapter;

import java.util.Collection;

public class Adapter {
    public static <T> Addable<T> collectionAdapter(Collection<T> collection) {
        return new AddableCollectionAdapter<T>(collection);
    }
}
