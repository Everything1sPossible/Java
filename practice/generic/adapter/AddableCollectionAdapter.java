package com.sjh.thinkinginjava.generic.adapter;

import java.util.Collection;

public class AddableCollectionAdapter<T> implements Addable<T> {
    private Collection<T> collection;

    public AddableCollectionAdapter(Collection<T> collection) {
        this.collection = collection;
    }

    public void add(T t) {
        collection.add(t);
    }
}
