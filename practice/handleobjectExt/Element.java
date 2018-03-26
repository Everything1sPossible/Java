package com.sjh.thinkinginjava.handleobjectExt;

public class Element {
    private String id;

    public Element(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Element && id.equals(((Element)obj).id);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize " + getClass().getSimpleName() + " " + id);
    }
}
