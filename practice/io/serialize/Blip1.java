package com.sjh.thinkinginjava.io.serialize;

import java.io.*;

public class Blip1 implements Externalizable {
    public Blip1() {
        System.out.println("Blip1 Constructor");
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip1.writeExternal");
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip1.readExternal");
    }
}
